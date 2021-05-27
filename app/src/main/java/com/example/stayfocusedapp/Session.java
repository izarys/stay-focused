package com.example.stayfocusedapp;

import android.os.CountDownTimer;

public class Session {
    public static final int COUNTDOWN_INTERVAL = 1000;

    CountDownTimer countDownTimer;
    long timeLeftInMillis;

    EventListener eventListener;

    public Session(long timeLeftInMillis) {
        this.timeLeftInMillis = timeLeftInMillis;

        createCountDownTimer();
    }

    public Session(long timeLeftInMillis, CountDownTimer countDownTimer) {
        this.timeLeftInMillis = timeLeftInMillis;
        this.countDownTimer = countDownTimer;
    }

    void createCountDownTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, COUNTDOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis -= COUNTDOWN_INTERVAL;
                eventListener.onTickEvent();
            }

            @Override
            public void onFinish() { eventListener.onFinishEvent(); }
        };
    }

    public void addListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    void startSession() {
        countDownTimer.start();
    }

    void endSession(boolean wasCanceled, DataUpdater dataUpdater, long sessionTime) {
        if(wasCanceled) {
            countDownTimer.cancel();
        }

        int minutes = (int) ((sessionTime / 1000) / 60);

        dataUpdater.updateStatistics(minutes, wasCanceled);
        dataUpdater.updateLevel();
    }

    long getTimeLeftInMillis() {
        return timeLeftInMillis;
    }
}
