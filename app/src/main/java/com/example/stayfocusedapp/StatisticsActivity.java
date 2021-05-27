package com.example.stayfocusedapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class StatisticsActivity extends BasicActivity {
    TextView failedSessionsCount;
    TextView totalTimeCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_statistics);

        failedSessionsCount = findViewById(R.id.failed_sessions_count);
        totalTimeCount = findViewById(R.id.total_time_count);

        updateStatisticsUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        updateStatisticsUI();
    }

    protected void updateStatisticsUI() {
        failedSessionsCount.setText(String.valueOf(Data.getInstance().getFailedSessions()));
        totalTimeCount.setText(String.valueOf(Data.getInstance().getTotalTime()) + " MINUTES");
    }
}
