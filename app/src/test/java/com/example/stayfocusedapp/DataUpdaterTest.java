package com.example.stayfocusedapp;

import junit.framework.TestCase;

import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataUpdaterTest extends TestCase {

    public void testUpdateFailedSessions() {
        Data data = Mockito.mock(Data.class);
        when(data.getFailedSessions()).thenReturn(1);

        DataUpdater dataUpdater = new DataUpdater(data);
        int delta = 3;

        dataUpdater.updateFailedSessions(delta);

        verify(data).setFailedSessions(1 + delta);
    }

    public void testUpdateLevel() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(Data.POINTS_TO_LEVEL_UP[1]);
        when(data.getLevel()).thenReturn(1);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateLevel();

        verify(data).setLevel(2);
    }

    public void testUpdateLevelMaxLevel() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(Data.POINTS_TO_LEVEL_UP[Data.MAX_LEVEL] + 10);
        when(data.getLevel()).thenReturn(Data.MAX_LEVEL);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateLevel();

        verify(data).setLevel(Data.MAX_LEVEL);
    }

    public void testUpdateLevelLevelUpTwoLevels() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(Data.POINTS_TO_LEVEL_UP[2]);
        when(data.getLevel()).thenReturn(1);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateLevel();

        verify(data).setLevel(3);
    }

    public void testUpdateStatisticsFailedSession() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(0);
        when(data.getTotalTime()).thenReturn(0);
        when(data.getFailedSessions()).thenReturn(0);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateStatistics(20, true);

        verify(data).setFailedSessions(1);
    }

    public void testUpdateStatisticsCompletedSessionSetFailedSessions() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(0);
        when(data.getTotalTime()).thenReturn(0);
        when(data.getFailedSessions()).thenReturn(0);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateStatistics(20, false);

        verify(data).setFailedSessions(0);
    }

    public void testUpdateStatisticsCompletedSessionSetTotalTime() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(0);
        when(data.getTotalTime()).thenReturn(0);
        when(data.getFailedSessions()).thenReturn(0);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateStatistics(20, false);

        verify(data).setTotalTime(20);
    }

    public void testUpdateStatisticsCompletedSessionSetPoints() {
        Data data = Mockito.mock(Data.class);
        when(data.getPoints()).thenReturn(0);
        when(data.getTotalTime()).thenReturn(0);
        when(data.getFailedSessions()).thenReturn(0);

        DataUpdater dataUpdater = new DataUpdater(data);

        dataUpdater.updateStatistics(20, false);

        verify(data).setPoints(20);
    }
}