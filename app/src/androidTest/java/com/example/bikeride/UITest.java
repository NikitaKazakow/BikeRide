package com.example.bikeride;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.bikeride.view.activity.LoginActivity;
import com.example.bikeride.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@LargeTest
public class UITest {

    private String stringLogin;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void initValidString() {
        stringLogin = "testString";
    }
    @Test
    public void pressButtonGo() {
        onView(withId(R.id.btn_login)).perform(click());
    }
    @Test
    public void inputLogin() {
        onView(withId(R.id.input_login))
                .perform(typeText(stringLogin), closeSoftKeyboard());
    }
    @Test
    public void inputPassword() {
        onView(withId(R.id.input_password))
                .perform(typeText(stringLogin), closeSoftKeyboard());
    }


}
