package com.example.frontend.ui.activity


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4

import com.example.frontend.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.hamcrest.Matchers.allOf
//import org.hamcrest.Matchers.anything
//import org.hamcrest.Matchers.`is`
import org.hamcrest.core.AllOf.allOf

@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class HomeTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun homeTest() {
        val textView = onView(
            allOf(
                withId(R.id.tv_mainTitle), withText("Pokedex"),
                withParent(
                    allOf(
                        withId(R.id.includeDate),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Pokedex")))

        val imageView = onView(
            allOf(
                withId(R.id.imagePokemon),
                withParent(withParent(withId(R.id.rv_home))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.nomePokemon), withText("Bulbasaur"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Bulbasaur")))

        val textView3 = onView(
            allOf(
                withId(R.id.candyPokemon), withText("Bulbasaur Candy"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Bulbasaur Candy")))

        val textView4 = onView(
            allOf(
                withId(R.id.candyCount), withText("Quantidade de Candy 25"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Quantidade de Candy 25")))

        val textView5 = onView(
            allOf(
                withId(R.id.candyCount), withText("Quantidade de Candy 25"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout3),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Quantidade de Candy 25")))
    }
}
