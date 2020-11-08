package com.honnalmanja.androidkotlinmvvm.presentation

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
class LoginToTaskListTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<TaskActivity>
            = ActivityScenarioRule(TaskActivity::class.java)

    @Before
    fun setUp() {

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

}