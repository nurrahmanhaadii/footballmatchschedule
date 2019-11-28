package id.haadii.submission.dicoding.footballmatchschedule.detailLeague

import android.content.Intent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import id.haadii.submission.dicoding.footballmatchschedule.R
import id.haadii.submission.dicoding.footballmatchschedule.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailLeagueActivityTest {
    @get:Rule
    val activityTestRule =
        object : ActivityTestRule<DetailLeagueActivity>(DetailLeagueActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val context = InstrumentationRegistry.getInstrumentation().targetContext
                val intent = Intent(context, DetailLeagueActivity::class.java)
                intent.putExtra("id", 4328)
                return intent
            }
        }

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @Test
    fun itemFound() {
        onView(withId(R.id.btn_see_match)).perform(click())

        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("Arsenal"),
            pressImeActionButton()
        )
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.tv_no_result)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rv_search)).check(matches(isDisplayed()))
    }

    @Test
    fun itemNotFound() {
        onView(withId(R.id.btn_see_match)).perform(click())

        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("asdf"),
            pressImeActionButton()
        )
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        onView(withId(R.id.tv_no_result)).check(matches(isDisplayed()))
    }
}