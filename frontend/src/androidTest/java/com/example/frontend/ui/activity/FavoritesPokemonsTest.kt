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
class FavoritesPokemonsTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun favoritesPokemonsTest() {
        val recyclerView = onView(
allOf(withId(R.id.rv_home),
childAtPosition(
withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
2)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        
        val materialButton = onView(
allOf(withId(R.id.btn_addFavorite), withText("ADICIONAR"),
childAtPosition(
allOf(withId(R.id.InfoFragment),
childAtPosition(
withId(R.id.nav_fragment),
0)),
5),
isDisplayed()))
        materialButton.perform(click())
        
        val materialTextView = onView(
allOf(withId(R.id.btn_return), withText("Voltar"),
childAtPosition(
allOf(withId(R.id.InfoFragment),
childAtPosition(
withId(R.id.nav_fragment),
0)),
0),
isDisplayed()))
        materialTextView.perform(click())
        
        val bottomNavigationItemView = onView(
allOf(withId(R.id.navigation_notifications), withContentDescription("Favoritos"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
1),
isDisplayed()))
        bottomNavigationItemView.perform(click())
        
        val textView = onView(
allOf(withId(R.id.tv_nameFavorite), withText("Bulbasaur"),
withParent(allOf(withId(R.id.linearLayout2),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView.check(matches(withText("Bulbasaur")))
        
        val textView2 = onView(
allOf(withId(R.id.tv_candyPokemon), withText("Bulbasaur Candy"),
withParent(allOf(withId(R.id.linearLayout2),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView2.check(matches(withText("Bulbasaur Candy")))
        
        val imageView = onView(
allOf(withId(R.id.imagePokemon), withContentDescription("Imagem do pokemom"),
withParent(allOf(withId(R.id.linearLayout2),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        imageView.check(matches(isDisplayed()))
        
        val imageView2 = onView(
allOf(withId(R.id.imageView3), withContentDescription("Pokemon classificado como favorito"),
withParent(withParent(withId(R.id.recyclerViewFavorites))),
isDisplayed()))
        imageView2.check(matches(isDisplayed()))
        
        val textView3 = onView(
allOf(withId(R.id.tv_mainTitle), withText("Pokedex"),
withParent(allOf(withId(R.id.include),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView3.check(matches(withText("Pokedex")))
        
        val textView4 = onView(
allOf(withId(R.id.tv_mainTitle), withText("Pokedex"),
withParent(allOf(withId(R.id.include),
withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java)))),
isDisplayed()))
        textView4.check(matches(withText("Pokedex")))
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
