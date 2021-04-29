package com.example.stayfocusedapp;

public class Level {
    public int level;
    public int maxLevel;
    public int points;
    public int[] pointsToLevelUp;

    public Level(int level, int maxLevel, int points, int[] pointsToLevelUp) {
        this.level = level;
        this.maxLevel = maxLevel;
        this.points = points;
        this.pointsToLevelUp = pointsToLevelUp;
    }

    public void updateLevel(int delta) {
        points += delta;
        while(level < maxLevel && pointsToLevelUp[level] <= points) {
            level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getPoints() {
        return points;
    }

    public int[] getPointsToLevelUp() {
        return pointsToLevelUp;
    }
}
