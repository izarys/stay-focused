package com.example.stayfocusedapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class LevelActivity extends BasicActivity {
    TextView levelTextView;
    TextView timeToLevelUp;
    MaterialProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        levelTextView = findViewById(R.id.level_text);
        timeToLevelUp = findViewById(R.id.time_to_level_up_count);
        progressBar = findViewById(R.id.level_progress_bar);

        updateLevelUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateLevelUI();
    }

    protected void updateLevelUI() {
        levelTextView.setText("LEVEL " + Data.getInstance().getLevel());

        timeToLevelUp.setText(String.valueOf(Data.getInstance().getTimeLeftToLevelUp()) + " MINUTES");

        progressBar.setMax(Data.getInstance().getPointsToLevelUp());
        progressBar.setProgress(Data.getInstance().getPoints());
    }
}