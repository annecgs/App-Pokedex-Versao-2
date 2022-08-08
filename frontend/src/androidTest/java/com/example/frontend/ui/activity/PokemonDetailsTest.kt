package com.example.frontend.ui.activity


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.frontend.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokemonDetailsTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun pokemonDetailsTest() {
        val recyclerView = onView(
allOf(withId(R.id.rv_home),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
        val imageView = onView(
allOf(withId(R.id.imageView), withContentDescription("Nome do pokemon"),
withParent(allOf(withId(R.id.InfoFragment),
withParent(withId(R.id.nav_fragment)))),
isDisplayed()))
        imageView.check(matches(isDisplayed()))
        
        val textView = onView(
allOf(withId(R.id.tv_pokemonNome), withText("Bulbasaur"),
withParent(allOf(withId(R.id.InfoFragment),
withParent(withId(R.id.nav_fragment)))),
isDisplayed()))
        textView.check(matches(withText("Bulbasaur")))
        
        val button = onView(
allOf(withId(R.id.btn_addFavorite), withText("ADICIONAR"),
withParent(allOf(withId(R.id.InfoFragment),
withParent(withId(R.id.nav_fragment)))),
isDisplayed()))
        button.check(matches(isDisplayed()))
        
        val textView2 = onView(
allOf(withId(R.id.textView3), withText("Informações"),
withParent(allOf(withId(R.id.linearLayout),
withParent(withId(R.id.InfoFragment)))),
isDisplayed()))
        textView2.check(matches(withText("Informações")))
        
        val textView3 = onView(
allOf(withText("Peso: "),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView3.check(matches(withText("Peso: ")))
        
        val textView4 = onView(
allOf(withId(R.id.tv_peso), withText("6.9 kg"),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView4.check(matches(withText("6.9 kg")))
        
        val textView5 = onView(
allOf(withText("Altura: "),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView5.check(matches(withText("Altura: ")))
        
        val textView6 = onView(
allOf(withId(R.id.tv_altura), withText("0.71 m"),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView6.check(matches(withText("0.71 m")))
        
        val textView7 = onView(
allOf(withText("Ovo: "),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView7.check(matches(withText("Ovo: ")))
        
        val textView8 = onView(
allOf(withText("Ovo: "),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView8.check(matches(withText("Ovo: ")))
        
        val textView9 = onView(
allOf(withId(R.id.tv_ovo), withText("2 km"),
withParent(withParent(withId(R.id.linearLayout))),
isDisplayed()))
        textView9.check(matches(withText("2 km")))
        
        val textView10 = onView(
allOf(withId(R.id.btn_return), withText("Voltar"),
withParent(allOf(withId(R.id.InfoFragment),
withParent(withId(R.id.nav_fragment)))),
isDisplayed()))
        textView10.check(matches(withText("Voltar")))
        
        val viewGroup = onView(
allOf(withId(androidx.appcompat.R.id.decor_content_parent),
withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
isDisplayed()))
        viewGroup.check(matches(isDisplayed()))
        }
    
    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
    }
