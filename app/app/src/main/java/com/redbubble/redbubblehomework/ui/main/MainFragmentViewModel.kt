package com.redbubble.redbubblehomework.ui.main

import androidx.lifecycle.ViewModel
import com.redbubble.redbubblehomework.api.Api
import com.redbubble.redbubblehomework.model.HomeModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class MainFragmentViewModel : ViewModel() {

    fun fetchData(): List<HomeModel> {
        val data = URL(Api.explore).readText()
        return parseResponse(data)
    }

    private fun parseResponse(data: String): List<HomeModel> {
        val items = arrayListOf<HomeModel>()
        val json = JSONObject(data)
        val home: JSONArray = json.getJSONArray("home") ?: return emptyList()

        for (i in 0 until home.length()) {
            val item = home.getJSONObject(i)

            try {
                items.add(
                    HomeModel(
                        isDetail = false,
                        type = item.getString("type"),
                        id = item.getString("id"),
                        title = item.getString("title"),
                        safeForWork = item.getBoolean("safeForWork"),
                        thumbnailUrl = item.getString("thumbnailUrl"),
                        amount = item.optJSONObject("price")?.optDouble("amount"),
                        currency = item.optJSONObject("price")?.getString("currency"),
                        artist = item.optString("artist")
                    )
                )
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return items
    }
}
