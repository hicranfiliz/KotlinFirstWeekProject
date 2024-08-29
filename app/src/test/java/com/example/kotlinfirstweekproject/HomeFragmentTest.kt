package com.example.kotlinfirstweekproject

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class HomeFragmentTest {

    private lateinit var scenario: FragmentScenario<HomeFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer<HomeFragment>()
    }

//    @Test
//    fun onSwitch6Clicked() {
//        scenario.onFragment { fragment ->
//            val switch6 = fragment.binding.swt6
//            val bottomNav = fragment.requireActivity().findViewById<BottomNavigationView>(R.id.bottomNav)
//            assertTrue(switch6.isChecked)
//            assertEquals(bottomNav.visibility, View.GONE)
//        }
//    }
}