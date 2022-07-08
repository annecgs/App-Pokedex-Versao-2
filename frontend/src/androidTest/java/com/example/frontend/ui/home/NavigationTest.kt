package com.example.pokedexapp.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.frontend.R
import com.example.frontend.ui.activity.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @Test
    fun navigationHome() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        // Tela inicial com todos os pokemons
        /*onView(withId(R.id.navigation_home))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_mainTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_mainDate))
            .check(matches(isDisplayed()))

        onView(withId(R.id.searchView))
            .check(matches(isDisplayed()))*/
        onView(withId(R.id.navigation_home)).check(matches(isDisplayed()))
    }

    @Test
    fun navagationFavoritos() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        // Tela de favoritos
        onView(withId(R.id.navigation_notifications))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_mainTitle))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_mainDate))
            .check(matches(isDisplayed()))
    }
}
