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
    var rawData = ""

    @Before
    public override fun setUp() {
        super.setUp()
        mainFragmentViewModel = MainFragmentViewModel()
        rawData = "{\n" +
                "    \"home\": [\n" +
                "       {\n" +
                "            \"type\": \"WORK\",\n" +
                "            \"id\": \"39670412\",\n" +
                "            \"title\": \"Bright rainbow with grunge texture\",\n" +
                "            \"artist\": \"Natalie Tyler\",\n" +
                "            \"safeForWork\": false,\n" +
                "            \"thumbnailUrl\": \"https://ih0.redbubble.net/image.850837360.0412/flat,500x,075,f.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"PRODUCT\",\n" +
                "            \"id\": \"39670936_beaut-razoo_6AQD3\",\n" +
                "            \"price\": {\n" +
                "                \"amount\": 40,\n" +
                "                \"currency\": \"AUD\"\n" +
                "            },\n" +
                "            \"safeForWork\": true,\n" +
                "            \"thumbnailUrl\": \"https://ih1.redbubble.net/image.850857794.0936/ctkr,x1862,front,black-c,324,409,600,600-bg,f8f8f8.u2.jpg\",\n" +
                "            \"title\": \"Sleeveless Top\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"WORK\",\n" +
                "            \"id\": \"350930\",\n" +
                "            \"title\": \"fox\",\n" +
                "            \"artist\": \"Natalie Tyler\",\n" +
                "            \"safeForWork\": true,\n" +
                "            \"thumbnailUrl\": \"https://ih1.redbubble.net/image.3460655.0930/flat,500x,075,f.jpg\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
    }

    @Test
    fun fetchData() {
        val data = mainFragmentViewModel.fetchData()
        assertTrue(data.isNotEmpty())
    }

    @Test
    fun parseResponseTitle() {
        val data = mainFragmentViewModel.parseResponse(rawData)
        assertTrue(data[1].title == "Sleeveless Top")
    }

    @Test
    fun parseResponseType() {
        val data = mainFragmentViewModel.parseResponse(rawData)
        assertTrue(data[1].type == "PRODUCT")
    }
}