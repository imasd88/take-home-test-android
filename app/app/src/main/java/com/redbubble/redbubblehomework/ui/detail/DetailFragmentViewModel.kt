package com.redbubble.redbubblehomework.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.redbubble.redbubblehomework.api.Api
import com.redbubble.redbubblehomework.model.DetailModel
import com.redbubble.redbubblehomework.model.HomeModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URL

class DetailFragmentViewModel : ViewModel() {

    val mutableLiveDataDetailModel = MutableLiveData<DetailModel>()
    val liveDataDetailModel: LiveData<DetailModel> = mutableLiveDataDetailModel

    fun fetchData(id: String): DetailModel {
        val data = URL(Api.getDetailEndpoint(id)).readText()
        return parseResponse(data)
    }

    fun parseResponse(data: String): DetailModel {
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

        return DetailModel(
            id = workDetailsObj.getString("id"),
            title = workDetailsObj.getString("title"),
            avatarUrl = workDetailsObj.optJSONObject("artist").getString("avatarUrl"),
            description = workDetailsObj.optJSONObject("artist").getString("description"),
            artistId = workDetailsObj.optJSONObject("artist").getString("id"),
            userName = workDetailsObj.optJSONObject("artist").getString("username"),
            imageUrl = workDetailsObj.optString("imageUrl"),
            safeForWork = workDetailsObj.getString("safeForWork"),
            shareUrl = workDetailsObj.optString("shareUrl"),
            availableProducts = items
        )
    }
}