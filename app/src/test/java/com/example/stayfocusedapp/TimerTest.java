package com.example.stayfocusedapp;

import android.media.AudioManager;
import android.os.CountDownTimer;

import junit.framework.TestCase;

import org.junit.Test;
import org.mockito.Mockito;

import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TimerTest extends TestCase {
    @Test
    public void testStartSessionRingerModeDuringSession() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        CountDownTimer countDownTimer = Mockito.mock(CountDownTimer.class);
        when(audioManager.getRingerMode()).thenReturn(1);
        Level level = Mockito.mock(Level.class);
        Statistics statistics = Mockito.mock(Statistics.class);

        Timer timer = new Timer(audioManager, level, statistics);
        timer.startSession(countDownTimer);

        verify(audioManager).setRingerMode(RINGER_MODE_SILENT);
    }

    @Test
    public void testStartSessionCountDownTimerRunning() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        CountDownTimer countDownTimer = Mockito.mock(CountDownTimer.class);
        Level level = Mockito.mock(Level.class);
        Statistics statistics = Mockito.mock(Statistics.class);

        Timer timer = new Timer(audioManager, level, statistics);
        timer.startSession(countDownTimer);

        verify(countDownTimer).start();
    }

    @Test
    public void testEndSessionSetPreviousRingerMode() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        Level level = Mockito.mock(Level.class);
        Statistics statistics = Mockito.mock(Statistics.class);

        Timer timer = new Timer(audioManager, level, statistics);
        timer.endSession(RINGER_MODE_NORMAL,0, 0,false);

        verify(audioManager).setRingerMode(RINGER_MODE_NORMAL);
    }

    @Test
    public void testEndSessionUpdateLevel() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        Level level = Mockito.mock(Level.class);
        Statistics statistics = Mockito.mock(Statistics.class);

        Timer timer = new Timer(audioManager, level, statistics);
        timer.endSession(RINGER_MODE_NORMAL,100, 0,false);

        verify(level).updateLevel(100);
    }

    @Test
    public void testEndSessionUpdateStatistics() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        Level level = Mockito.mock(Level.class);
        Statistics statistics = Mockito.mock(Statistics.class);

        Timer timer = new Timer(audioManager, level, statistics);
        timer.endSession(RINGER_MODE_NORMAL,0, 60,false);

        verify(statistics).updateStatistics(60,false);
    }


}