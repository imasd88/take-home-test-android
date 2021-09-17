package com.redbubble.redbubblehomework.ui.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentViewModelTest : TestCase() {

    //basic tests only

    private lateinit var mainFragmentViewModel: MainFragmentViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        mainFragmentViewModel = MainFragmentViewModel()
    }

    @Test
    fun fetchData() {
        val data = mainFragmentViewModel.fetchData()
        assertTrue(data.isNotEmpty())
    }
}