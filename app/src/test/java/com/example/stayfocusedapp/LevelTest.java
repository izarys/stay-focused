package com.example.stayfocusedapp;

import junit.framework.TestCase;
import org.junit.Test;

public class LevelTest extends TestCase {
    @Test
    public void testUpdateLevelLevelUp() {
        Level level = new Level(1,5, 0, new int[] {0, 10, 25, 45, 70, 100});
        level.updateLevel(10);
        assertEquals(2, level.getLevel());
    }

    @Test
    public void testUpdateLevelMaxLevel() {
        Level level = new Level(1, 1, 0, new int[] {0, 0});
        level.updateLevel(5);
        assertEquals(1, level.getLevel());
    }

    @Test
    public void testUpdateLevelLevelUpTwoLevels() {
        Level level = new Level(1,5, 0, new int[] {0, 10, 25, 45, 70, 100});
        level.updateLevel(30);
        assertEquals(3, level.getLevel());
    }
}