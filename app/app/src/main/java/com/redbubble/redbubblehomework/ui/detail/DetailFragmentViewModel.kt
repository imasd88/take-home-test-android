package com.redbubble.redbubblehomework.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import com.redbubble.redbubblehomework.api.Api
import com.redbubble.redbubblehomework.model.DetailModel
import com.redbubble.redbubblehomework.model.HomeModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL
import kotlin.math.log10

class DetailFragmentViewModel : ViewModel() {

    private var availableProductsList = arrayListOf<HomeModel>()
    private var workDetails: DetailModel? = null

    fun fetchData(id: String): String {
         val temp = URL(Api.getDetailEndpoint(id)).readText()
        return temp
//        return parseResponse(data)
    }

    fun getAvailableProducts() = availableProductsList
    fun getWorkDetails() = workDetails

    fun parseResponse(data: String) {
        val items = arrayListOf<HomeModel>()
        val json = JSONObject(data)
        val workDetailsObj: JSONObject = json.getJSONObject("workDetails")
        val availableProductsObj: JSONArray =
            workDetailsObj.getJSONArray("availableProducts")


        for (i in 0 until availableProductsObj.length()) {
            val item = availableProductsObj.getJSONObject(i)

            try {
                items.add(
                    HomeModel(
                        type = "",
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

        availableProductsList = items


        val workDetailItems = DetailModel(
            id = workDetailsObj.getString("id"),
            title = workDetailsObj.getString("title"),
            artist = workDetailsObj.optString("artist"),
            avatarUrl = workDetailsObj.optJSONObject("artist").getString("avatarUrl"),
            description = workDetailsObj.optString("description"),
            artistId = workDetailsObj.optString("id"),
            userName = "",
            imageUrl = workDetailsObj.optString("imageUrl"),
            workDetailSafeForWork = "",
            shareUrl = "",
            availableProducts = items
        )

        workDetails = workDetailItems
    }
}