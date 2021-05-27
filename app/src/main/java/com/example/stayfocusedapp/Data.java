package com.example.stayfocusedapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Data {
    private static Data instance;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LEVEL = "level";
    public static final String POINTS = "points";
    public static final String TOTAL_TIME = "totalTime";
    public static final String FAILED_SESSIONS = "failedSessions";
    public static final String SESSION_TIME = "sessionTime";
    public static final long[] SESSION_TIMES_TABLE = {900000, 900000, 1800000, 3600000, 5400000};
    public static final int MAX_LEVEL = 10;
    public static final int[] POINTS_TO_LEVEL_UP = {0, 30, 90, 270, 810, 2430, 7290, 21870, 65610, 196830, 590490};

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Data() {}

    public static Data getInstance() {
        if(instance == null){
            instance = new Data();
        }
        return instance;
    }

    public void init(Context context) { sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE); }


    public long getSessionTime() {
        return sharedPreferences.getLong(SESSION_TIME, 900000);
    }

    public int getLevel() {
        return sharedPreferences.getInt(LEVEL, 1);
    }

    public int getPoints() {
        return sharedPreferences.getInt(POINTS, 0);
    }

    public int getTotalTime() {
        return sharedPreferences.getInt(TOTAL_TIME, 0);
    }

    public int getFailedSessions() {
        return sharedPreferences.getInt(FAILED_SESSIONS, 0);
    }

    public int getPointsToLevelUp() {
        return POINTS_TO_LEVEL_UP[getLevel()];
    }

    public int getTimeLeftToLevelUp() { return getPointsToLevelUp() - getPoints(); }

    public void setPoints(int points) {
        editor = sharedPreferences.edit();
        editor.putInt(POINTS, points);
        editor.apply();
    }

    public void setTotalTime(int totalTime) {
        editor = sharedPreferences.edit();
        editor.putInt(TOTAL_TIME, totalTime);
        editor.apply();
    }

    public void setFailedSessions(int failedSessions) {
        editor = sharedPreferences.edit();
        editor.putInt(FAILED_SESSIONS, failedSessions);
        editor.apply();
    }

    public void setSessionTime(int pos) {
        editor = sharedPreferences.edit();
        editor.putLong(SESSION_TIME, SESSION_TIMES_TABLE[pos]);
        editor.apply();
    }

    public void setLevel(int level) {
        editor = sharedPreferences.edit();
        editor.putInt(LEVEL, level);
        editor.apply();
    }
}
