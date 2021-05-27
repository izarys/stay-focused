package com.example.stayfocusedapp;

import android.media.AudioManager;

import static android.media.AudioManager.RINGER_MODE_SILENT;

public class AudioManagerHandler {
    int ringerMode;
    AudioManager audioManager;

    AudioManagerHandler(AudioManager audioManager) {
        this.audioManager = audioManager;
        ringerMode = audioManager.getRingerMode();
    }

    public void setRingerModeSilent() {
        audioManager.setRingerMode(RINGER_MODE_SILENT);
    }

    public void setPreviousRingerMode() {
        audioManager.setRingerMode(ringerMode);
    }
}
