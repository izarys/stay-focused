package com.example.stayfocusedapp;

import android.media.AudioManager;
import android.os.CountDownTimer;
import static android.media.AudioManager.RINGER_MODE_SILENT;


public class Timer {
    AudioManager audioManager;
    Level level;
    Statistics statistics;

    public Timer(AudioManager audioManager, Level level, Statistics statistics) {
        this.audioManager = audioManager; //(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        this.level = level;
        this.statistics = statistics;
    }

    public void startSession(CountDownTimer countDownTimer) {
        int ringerMode = audioManager.getRingerMode();
        audioManager.setRingerMode(RINGER_MODE_SILENT);

        countDownTimer.start();
    }

    public void endSession(int ringerMode, int points, int time, boolean wasCanceled) {
        audioManager.setRingerMode(ringerMode);
        level.updateLevel(points);
        statistics.updateStatistics(time, wasCanceled);
    }
}

