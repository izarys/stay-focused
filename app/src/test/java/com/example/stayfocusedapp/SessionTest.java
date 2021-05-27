package com.example.stayfocusedapp;

import android.os.CountDownTimer;

import junit.framework.TestCase;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class SessionTest extends TestCase {

    public void testStartSessionCountDownTimerRunning() {
        CountDownTimer countDownTimer = Mockito.mock(CountDownTimer.class);

        Session session = new Session(0, countDownTimer);
        session.startSession();

        verify(countDownTimer).start();
    }

    public void testEndSessionUpdateLevel() {
        DataUpdater dataUpdater = Mockito.mock(DataUpdater.class);

        Session session = new Session(0);
        session.endSession(false, dataUpdater, 0);

        verify(dataUpdater).updateLevel();
    }

    public void testEndSessionUpdateStatistics() {
        DataUpdater dataUpdater = Mockito.mock(DataUpdater.class);

        Session session = new Session(0);
        session.endSession(false, dataUpdater, 0);

        verify(dataUpdater).updateStatistics(0, false);
    }
}