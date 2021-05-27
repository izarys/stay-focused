package com.example.stayfocusedapp;

import android.media.AudioManager;

import junit.framework.TestCase;

import org.mockito.Mockito;

import static android.media.AudioManager.RINGER_MODE_NORMAL;
import static android.media.AudioManager.RINGER_MODE_SILENT;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AudioManagerHandlerTest extends TestCase {

    public void testSetRingerModeSilent() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        when(audioManager.getRingerMode()).thenReturn(1);

        AudioManagerHandler audioManagerHandler = new AudioManagerHandler(audioManager);
        audioManagerHandler.setRingerModeSilent();

        verify(audioManager).setRingerMode(RINGER_MODE_SILENT);
    }

    public void testSetPreviousRingerMode() {
        AudioManager audioManager = Mockito.mock(AudioManager.class);
        when(audioManager.getRingerMode()).thenReturn(RINGER_MODE_NORMAL);

        AudioManagerHandler audioManagerHandler = new AudioManagerHandler(audioManager);
        audioManagerHandler.setPreviousRingerMode();

        verify(audioManager).setRingerMode(RINGER_MODE_NORMAL);
    }
}