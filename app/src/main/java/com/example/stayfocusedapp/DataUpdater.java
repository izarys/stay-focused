package com.example.stayfocusedapp;

public class DataUpdater {
    Data data;

    public DataUpdater(Data data) {
        this.data = data;
    }

    public void updateFailedSessions(int delta) {
        data.setFailedSessions(data.getFailedSessions() + delta);
    }

    public void updateLevel() {
        int points = data.getPoints();
        int level = data.getLevel();
        while(level < Data.MAX_LEVEL && Data.POINTS_TO_LEVEL_UP[level] <= points) {
            level++;
        }
        data.setLevel(level);
    }

    public void updateStatistics(int deltaTime, boolean failedSession) {
        if(!failedSession) {
            data.setTotalTime(data.getTotalTime() + deltaTime);
            data.setPoints(data.getPoints() + deltaTime);
        }
        data.setFailedSessions(data.getFailedSessions() + (failedSession ? 1 : 0));
    }
}
