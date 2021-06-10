package com.example.stayfocusedapp;

import android.content.Context;
import android.media.AudioManager;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
@LargeTest
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void increaseLevelAfter15MinSession() throws InterruptedException {
        int level = 2;
        Data.getInstance().setLevel(level);
        int points = Data.getInstance().getPointsToLevelUp() - 10;
        Data.getInstance().setPoints(points);
        Data.getInstance().setTotalTime(points);

        Data.getInstance().setSessionTime(1);

        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);
        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));

        onView(withId(R.id.level_button)).perform(click());
        assertEquals(level + 1, Data.getInstance().getLevel());
    }

    @Test
    public void increaseLevelAfter30MinSession() throws InterruptedException {
        int level = 2;
        Data.getInstance().setLevel(level);
        int points = Data.getInstance().getPointsToLevelUp() - 30;
        Data.getInstance().setPoints(points);
        Data.getInstance().setTotalTime(points);

        Data.getInstance().setSessionTime(2);

        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);
        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));

        onView(withId(R.id.level_button)).perform(click());
        assertEquals(level + 1, Data.getInstance().getLevel());
    }

    @Test
    public void dontIncreaseLevelAfter15MinSession() throws InterruptedException {
        int level = 2;
        Data.getInstance().setLevel(level);
        int points = Data.getInstance().getPointsToLevelUp() - 16;
        Data.getInstance().setPoints(points);
        Data.getInstance().setTotalTime(points);

        Data.getInstance().setSessionTime(1);

        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);
        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));

        onView(withId(R.id.level_button)).perform(click());
        assertEquals(level, Data.getInstance().getLevel());
    }

    @Test
    public void quitSessionSetFor15min() {
        Data.getInstance().setSessionTime(1);
        onView(withId(R.id.timer_action_button)).perform(click()); // start
        onView(withId(R.id.timer_action_button)).perform(click()); // quit

        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));
    }

    @Test
    public void quitSessionSetFor30min() {
        Data.getInstance().setSessionTime(2);
        onView(withId(R.id.timer_action_button)).perform(click()); // start
        onView(withId(R.id.timer_action_button)).perform(click()); // quit

        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));
    }

    @Test
    public void quitSessionSetFor60min() {
        Data.getInstance().setSessionTime(3);
        onView(withId(R.id.timer_action_button)).perform(click()); // start
        onView(withId(R.id.timer_action_button)).perform(click()); // quit

        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));
    }


    @Test
    public void addPointsToTotalTimeAfter15MinSession() throws InterruptedException {
        Data.getInstance().setSessionTime(1);

        int totalTime = Data.getInstance().getTotalTime();
        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);

        onView(withId(R.id.statistics_button)).perform(click());
        assertEquals(totalTime + 15, Data.getInstance().getTotalTime());
    }

    @Test
    public void addPointsToTotalTimeAfter30MinSession() throws InterruptedException {
        Data.getInstance().setSessionTime(2);

        int totalTime = Data.getInstance().getTotalTime();
        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);

        onView(withId(R.id.statistics_button)).perform(click());
        assertEquals(totalTime + 30, Data.getInstance().getTotalTime());
    }

    @Test
    public void turnOnTheTimerFor15Minutes() {
        Data.getInstance().setSessionTime(1);
        onView(withId(R.id.timer_action_button)).perform(click());

        onView(withId(R.id.timer_action_button)).check(matches(isChecked()));
    }

    @Test
    public void turnOnTheTimerFor30Minutes() {
        Data.getInstance().setSessionTime(2);
        onView(withId(R.id.timer_action_button)).perform(click());

        onView(withId(R.id.timer_action_button)).check(matches(isChecked()));
    }

    @Test
    public void turnOnTheTimerFor60Minutes() {
        Data.getInstance().setSessionTime(3);
        onView(withId(R.id.timer_action_button)).perform(click());

        onView(withId(R.id.timer_action_button)).check(matches(isChecked()));
    }

    @Test
    public void goSilentModeFromNormalMode() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        onView(withId(R.id.timer_action_button)).perform(click());
        assertEquals(AudioManager.RINGER_MODE_SILENT, audioManager.getRingerMode());
    }

    @Test
    public void goSilentModeFromVibrateMode() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

        onView(withId(R.id.timer_action_button)).perform(click());
        assertEquals(AudioManager.RINGER_MODE_SILENT, audioManager.getRingerMode());
    }

    @Test
    public void goSilentModeFromSilentMode() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        onView(withId(R.id.timer_action_button)).perform(click());
        assertEquals(AudioManager.RINGER_MODE_SILENT, audioManager.getRingerMode());
    }

    @Test
    public void goBackToNormalModeAfterSessionEnds() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

        onView(withId(R.id.timer_action_button)).perform(click()); // start session
        onView(withId(R.id.timer_action_button)).perform(click()); // end session
        assertEquals(AudioManager.RINGER_MODE_NORMAL, audioManager.getRingerMode());
    }

    @Test
    public void goBackToVibrateModeAfterSessionEnds() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

        onView(withId(R.id.timer_action_button)).perform(click()); // start session
        onView(withId(R.id.timer_action_button)).perform(click()); // end session
        assertEquals(AudioManager.RINGER_MODE_VIBRATE, audioManager.getRingerMode());
    }

    @Test
    public void goBackToSilentModeAfterSessionEnds() {
        MainActivity mainActivity = mainActivityRule.getActivity();
        AudioManager audioManager = (AudioManager) mainActivity.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

        onView(withId(R.id.timer_action_button)).perform(click()); // start session
        onView(withId(R.id.timer_action_button)).perform(click()); // end session
        assertEquals(AudioManager.RINGER_MODE_SILENT, audioManager.getRingerMode());
    }

    @Test
    public void checkDisplayingStatisticsFromMainActivity() {
        onView(withId(R.id.statistics_button)).perform(click());

        onView(withId(R.id.total_time_text)).check(matches(isDisplayed()));
        onView(withId(R.id.total_time_count)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_text)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_count)).check(matches(isDisplayed()));
    }

    @Test
    public void checkDisplayingStatisticsFromLevel() {
        onView(withId(R.id.level_button)).perform(click());
        onView(withId(R.id.statistics_button)).perform(click());

        onView(withId(R.id.total_time_text)).check(matches(isDisplayed()));
        onView(withId(R.id.total_time_count)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_text)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_count)).check(matches(isDisplayed()));
    }

    @Test
    public void checkDisplayingStatisticsFromStatistics() {
        onView(withId(R.id.statistics_button)).perform(click());
        onView(withId(R.id.statistics_button)).perform(click());

        onView(withId(R.id.total_time_text)).check(matches(isDisplayed()));
        onView(withId(R.id.total_time_count)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_text)).check(matches(isDisplayed()));
        onView(withId(R.id.failed_sessions_count)).check(matches(isDisplayed()));
    }

    @Test
    public void decreaseTimeToLevelUpAfter15MinSession() throws InterruptedException {
        Data.getInstance().setSessionTime(1);
        int points = Data.getInstance().getPointsToLevelUp() - 20;
        Data.getInstance().setPoints(points);
        Data.getInstance().setTotalTime(points);
        int timeLeftToLevelUp = Data.getInstance().getTimeLeftToLevelUp();
        onView(withId(R.id.timer_action_button)).perform(click());

        TimeUnit.MILLISECONDS.sleep(Data.getInstance().getSessionTime() + 1000);
        onView(withId(R.id.timer_action_button)).check(matches(isNotChecked()));

        onView(withId(R.id.level_button)).perform(click());
        assertEquals(timeLeftToLevelUp - 15, Data.getInstance().getTimeLeftToLevelUp());
    }



}
