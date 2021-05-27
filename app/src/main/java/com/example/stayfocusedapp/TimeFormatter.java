package com.example.stayfocusedapp;

public class TimeFormatter {
    public static String formatTimeToString(long timeInMillis) {
        int minutes = (int) ((timeInMillis / 1000) / 60);
        int seconds = (int) ((timeInMillis / 1000) % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }
}
