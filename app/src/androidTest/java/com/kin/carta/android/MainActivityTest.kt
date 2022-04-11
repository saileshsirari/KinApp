package com.kin.carta.android

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.kin.carta.android.adapters.CaseStudiesAdaptor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(MainActivity::class.java, true)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun openCases() {
        onView(isRoot()).perform(waitFor(2000))
        onView(Matchers.allOf(withId(R.id.caseStudiesRecyclerView), isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CaseStudiesAdaptor.ViewHolder>(
                    0,
                    click()
                )
            );
        onView(isRoot()).perform(waitFor(3000))
        onView(withId(R.id.caseStudyDetailRecyclerView)).check(matches(isDisplayed()))
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
