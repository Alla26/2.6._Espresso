package ru.kkuzmichev.simpleappforespresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

        @Rule
        public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
                new ActivityScenarioRule<>(MainActivity.class);

        @Test
        public void mainActivitySettingsTest() {

            ViewInteraction imageView = onView(
                    Matchers.allOf(
                            withParent(withParent(withId(R.id.toolbar))),
                            isDisplayed()));
            imageView.perform(click());

            ViewInteraction textView = onView(
                    Matchers.allOf(withId(androidx.appcompat.R.id.title),
                            withParent(withParent(withId(androidx.appcompat.R.id.content))),
                            isDisplayed()));
            textView.check(matches(withParent(withParent(withId(androidx.appcompat.R.id.content)))));
            Intents.init();
            textView.perform(click());
            intended(hasData("https://google.com"));
            intended(hasAction(Intent.ACTION_VIEW));
            Intents.release();
        }

        @Test
        public void mainActivityTest() {

            ViewInteraction textView = onView(withId(R.id.text_home));
            textView.check(matches(isDisplayed()));
            textView.check(matches(withText("This is home fragment")));
        }
}
