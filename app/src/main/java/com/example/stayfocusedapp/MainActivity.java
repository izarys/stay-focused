package com.example.stayfocusedapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import static com.example.stayfocusedapp.TimeFormatter.formatTimeToString;

public class MainActivity extends BasicActivity implements EventListener {

    public static final int INCREMENT = 1000;

    AudioManagerHandler audioManagerHandler;

    ImageView settingsImage;
    TextView timeLeftTextView;

    MaterialProgressBar progressBar;
    ToggleButton timerActionButton;
    Spinner spinner;

    Session session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data.getInstance().init(this);

        settingsImage = findViewById(R.id.settings_icon);
        timeLeftTextView = findViewById(R.id.timer_countdown);
        progressBar = findViewById(R.id.progress_bar);
        timerActionButton = findViewById(R.id.timer_action_button);
        spinner = findViewById(R.id.choose_time_spinner);

        timerActionButton.setOnClickListener(v -> {
            if(timerActionButton.isChecked()) {
                startTimer();
            } else {
                cancelTimer();
            }
        });


        setUpSpinner();

        setUpNotificationManager();

        updateUI();
    }

    void setUpNotificationManager() {
        // The application needs a permission to change ringer mode when a session starts and ends
        // This method asks the user for that permission
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {
            Intent intent = new Intent(android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }
    }

    void setUpSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Data.getInstance().setSessionTime(position);
                timeLeftTextView.setText(formatTimeToString(Data.getInstance().getSessionTime()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    void updateUI() {
        timeLeftTextView.setText(formatTimeToString(Data.getInstance().getSessionTime()));

        progressBar.setMax((int) Data.getInstance().getSessionTime());
        progressBar.setProgress((int) Data.getInstance().getSessionTime());
    }

    void startTimer() {
        session = new Session(Data.getInstance().getSessionTime());
        session.addListener(this);
        session.startSession();

        progressBar.setProgress(0);

        audioManagerHandler = new AudioManagerHandler((AudioManager)getSystemService(AUDIO_SERVICE));
        audioManagerHandler.setRingerModeSilent();

        spinner.setVisibility(View.INVISIBLE);
        settingsImage.setVisibility(View.INVISIBLE);
    }

    private void cancelTimer() {
        timerActionButton.setChecked(false);
        stopTimer(true);
    }

    private void stopTimer(boolean wasCanceled) {
        session.endSession(wasCanceled, new DataUpdater(Data.getInstance()), Data.getInstance().getSessionTime());

        timerActionButton.setChecked(false);
        progressBar.setProgress((int) Data.getInstance().getSessionTime());
        timeLeftTextView.setText(formatTimeToString(Data.getInstance().getSessionTime()));

        audioManagerHandler.setPreviousRingerMode();

        spinner.setVisibility(View.VISIBLE);
        settingsImage.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateUI();
    }

    @Override
    public void onFinishEvent() {
        stopTimer(false);
    }

    @Override
    public void onTickEvent() {
        progressBar.incrementProgressBy(INCREMENT);
        timeLeftTextView.setText(formatTimeToString(session.getTimeLeftInMillis()));
    }
}