package com.redbubble.redbubblehomework.api

object Api {
    private val baseUrl = "https://take-home-test.herokuapp.com/bff/";

    val explore = baseUrl.plus("explore.json")

    fun getDetailEndpoint(id: String) = baseUrl.plus("/workDetails").plus("$id.json")
}