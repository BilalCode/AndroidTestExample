package com.example.testingandroid

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.core.StringStartsWith.*

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.testingandroid", appContext.packageName)
    }

    @Test
    fun findRide(){
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.rideSummary)).check(matches(isDisplayed()))
        onView(withText(containsString("Saver"))).check(matches(isDisplayed()))

        val numberResult: ViewInteraction = onView(withId(R.id.tvTimeToReach))
        val textTimeToReach = getText(numberResult).split(" ");
        val time = textTimeToReach[0].toInt()
        assertTrue(time<=20)

        val tvDistance: ViewInteraction = onView(withId(R.id.tvDistance))
        val textDistance = getText(tvDistance).split(" ");
        val distance = textDistance[0].toInt()
        assertTrue(distance<=5)

        val tvAvailableSeats: ViewInteraction = onView(withId(R.id.tvAvailableSeats))
        val textAvailableSeats = getText(tvAvailableSeats).split(" ");
        val availableSeats = textAvailableSeats[0].toInt()
        assertTrue(availableSeats>=1)
    }

    private fun getText(matcher: ViewInteraction): String {
        var text = String()
        matcher.perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "Text of the view"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                text = tv.text.toString()
            }
        })

        return text
    }
}