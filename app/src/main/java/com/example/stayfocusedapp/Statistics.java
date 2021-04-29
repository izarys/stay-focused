package com.example.stayfocusedapp;

public class Statistics {
    int totalTime; // in minutes
    int failedSessions;

    public int getTotalTime() {
        return totalTime;
    }

    public int getFailedSessions() {
        return failedSessions;
    }

    public Statistics(int totalTime, int failedSessions) {
        this.totalTime = totalTime;
        this.failedSessions = failedSessions;
    }

    public void updateStatistics(int deltaTime, boolean failedSession) {
        totalTime += deltaTime;
        failedSessions += (failedSession ? 1 : 0);
    }

}
