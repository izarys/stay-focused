package com.example.stayfocusedapp;

import junit.framework.TestCase;

import org.junit.Test;

public class StatisticsTest extends TestCase {
    @Test
    public void testUpdateStatisticsTotalTime() {
        Statistics statistics = new Statistics(60, 0);
        statistics.updateStatistics(40, false);
        assertEquals(100, statistics.getTotalTime());
    }

    @Test
    public void testUpdateStatisticsFailedSessionsFalse() {
        Statistics statistics = new Statistics(0, 5);
        statistics.updateStatistics(0, false);
        assertEquals(5, statistics.getFailedSessions());
    }

    @Test
    public void testUpdateStatisticsFailedSessionsTrue() {
        Statistics statistics = new Statistics(0, 5);
        statistics.updateStatistics(0, true);
        assertEquals(6, statistics.getFailedSessions());
    }
}