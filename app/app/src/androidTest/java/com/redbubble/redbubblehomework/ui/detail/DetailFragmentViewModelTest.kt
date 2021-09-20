package com.redbubble.redbubblehomework.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailFragmentViewModelTest : TestCase() {

    //basic tests only

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailFragmentViewModel: DetailFragmentViewModel
    private var rawData = ""

    @Before
    public override fun setUp() {
        super.setUp()
        detailFragmentViewModel = DetailFragmentViewModel()
        rawData = "{\n" +
                "    \"workDetails\": {\n" +
                "      \"artist\": {\n" +
                "        \"avatarUrl\": \"https://ih0.redbubble.net/avatar.72134.140x140.jpg\",\n" +
                "        \"description\": \"44 Designs\",\n" +
                "        \"id\": \"1338962\",\n" +
                "        \"username\": \"natalietyler\"\n" +
                "      },\n" +
                "      \"id\": \"39670936\",\n" +
                "      \"imageUrl\": \"https://ih0.redbubble.net/image.850857816.0936/flat,1000x,075,f.jpg\",\n" +
                "      \"safeForWork\": true,\n" +
                "      \"shareUrl\": \"https://www.redbubble.com/shop/ap/39670936?utm_source=rb-native-app&utm_campaign=share-work&utm_medium=other\",\n" +
                "      \"title\": \"Orange, pink and blue coral reef abstract \",\n" +
                "      \"availableProducts\": [\n" +
                "      {\n" +
                "        \"id\": \"39670936_ripper-rissole_1YYVU\",\n" +
                "        \"price\": {\n" +
                "          \"amount\": 40.6,\n" +
                "          \"currency\": \"AUD\"\n" +
                "        },\n" +
                "        \"safeForWork\": false,\n" +
                "        \"thumbnailUrl\": \"https://ih1.redbubble.net/image.850858326.0936/gptr,1265x,front,black-c,330,402,600,600-bg,f8f8f8.u2.jpg\",\n" +
                "        \"title\": \"Graphic T-Shirt\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": \"39670936_beaut-razoo_6AQD3\",\n" +
                "        \"price\": {\n" +
                "          \"amount\": 40,\n" +
                "          \"currency\": \"AUD\"\n" +
                "        },\n" +
                "        \"safeForWork\": true,\n" +
                "        \"thumbnailUrl\": \"https://ih1.redbubble.net/image.850857794.0936/ctkr,x1862,front,black-c,324,409,600,600-bg,f8f8f8.u2.jpg\",\n" +
                "        \"title\": \"Sleeveless Top\"\n" +
                "      }\n" +
                "    ]\n" +
                "    }\n" +
                "  }"
    }

    @Test
    fun fetchData() {
        val data = detailFragmentViewModel.fetchData("350930")
        assertTrue(data.isNotEmpty())
    }

    @Test
    fun parseResponseTitle() {
        val data = detailFragmentViewModel.parseResponse(rawData)
        assertTrue(data.title == "Orange, pink and blue coral reef abstract ")
    }

    @Test
    fun parseResponseAvailableProducts() {
        val data = detailFragmentViewModel.parseResponse(rawData)
        assertTrue(data.availableProducts.isNotEmpty())
    }

    @Test
    fun parseResponseAvailableProductsData() {
        val data = detailFragmentViewModel.parseResponse(rawData)
        assertTrue(data.availableProducts[0].title == "Graphic T-Shirt")
    }
}