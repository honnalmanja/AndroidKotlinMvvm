package com.honnalmanja.androidkotlinmvvm.presentation.view.user

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import com.honnalmanja.androidkotlinmvvm.R
import com.honnalmanja.androidkotlinmvvm.presentation.view.TaskActivity
import com.honnalmanja.androidkotlinmvvm.presentation.view.task.TaskListFragment
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NoUserTokenLoginPageTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<TaskActivity>
            = ActivityScenarioRule(TaskActivity::class.java)

    @Before
    fun setUp() {

    }

    @Test
    fun widgets_visible_login_screen() {

        Espresso.onView(withId(R.id.login_email_et))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.login_password_et))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.login_login_btn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.login_sign_up_btn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_invalid_email() {
        Espresso.onView(withId(R.id.login_email_et))
            .perform(ViewActions.typeText("Email"), click())
        Espresso.onView(withId(R.id.login_password_et))
            .perform(ViewActions.typeText("Email"), click())
        Espresso.onView(withId(R.id.login_login_btn))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.login_email_til))
            .check(matches(hasTextInputLayoutHintText("Enter valid email Address")))
    }

    @Test
    fun test_empty_email() {
        Espresso.onView(withId(R.id.login_email_et))
            .perform(ViewActions.typeText(""), click())
        Espresso.onView(withId(R.id.login_login_btn))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.login_email_til))
            .check(matches(hasTextInputLayoutHintText("Enter Email")))
    }

    @Test
    fun test_empty_password() {
        Espresso.onView(withId(R.id.login_email_et))
            .perform(ViewActions.typeText("man@fd.co"), click())
        Espresso.onView(withId(R.id.login_login_btn))
            .perform(ViewActions.click())
        Espresso.onView(withId(R.id.login_password_til))
            .check(matches(hasTextInputLayoutHintText("Enter Password")))
    }

    @Test
    fun does_move_to_task_list() {

        Espresso.onView(withId(R.id.login_email_et))
            .perform(ViewActions.typeText("manju@example.com"), click())
        Espresso.onView(withId(R.id.login_password_et))
            .perform(ViewActions.typeText("manju@123"), click())
        Espresso.onView(withId(R.id.login_login_btn))
            .perform(ViewActions.click())
        val scenario = launchFragmentInContainer<TaskListFragment>()
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    fun hasTextInputLayoutHintText(expectedErrorText: String): Matcher<View> = object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) { }

        override fun matchesSafely(item: View?): Boolean {
            if (item !is TextInputLayout) return false
            val error = item.error ?: return false
            val hint = error.toString()
            return expectedErrorText == hint
        }
    }

}