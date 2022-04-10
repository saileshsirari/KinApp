package com.kin.carta.android

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kin.carta.android.data.CaseStudiesAdaptor
import com.kin.carta.android.utilities.TestUtil
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class CaseStydyScDetailFragmentTest {
    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(MainActivity::class.java, true)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    fun titleIsDisplayed() {
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(2000))
        val testCaseStudy = TestUtil.getCaseStudy()
        Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.caseStudiesRecyclerView),
                ViewMatchers.isDisplayed()
            )
        )
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CaseStudiesAdaptor.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            );
        Espresso.onView(ViewMatchers.withId(R.id.caseStudyDetailRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withText(testCaseStudy.title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> = ViewMatchers.isRoot()
            override fun getDescription(): String = "wait for $delay milliseconds"
            override fun perform(uiController: UiController, v: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}
