package com.redbubble.redbubblehomework.ui.main

import com.redbubble.redbubblehomework.model.HomeModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class MainFragmentViewModelTest1 {
    private lateinit var mainFragmentViewModel: MainFragmentViewModel
    val testHomeModel = arrayListOf<HomeModel>()

    @Before
    fun setUp() {
        mainFragmentViewModel = Mockito.mock(MainFragmentViewModel::class.java)
        for (i in 0..3)
            testHomeModel.add(
                HomeModel(
                    isDetail = false,
                    type = "PRODUCT",
                    id = "123",
                    title = "test title",
                    safeForWork = true,
                    thumbnailUrl = "",
                    amount = 100.55,
                    currency = "AUD",
                    artist = "artist"
                )
            )
    }

    @Test
    fun fetchData() {
        `when`(mainFragmentViewModel.fetchData()).thenReturn(testHomeModel)
    }
}