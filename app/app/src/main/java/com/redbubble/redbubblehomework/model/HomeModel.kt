package com.redbubble.redbubblehomework.model

data class HomeModel(
    val isDetail: Boolean,
    val type: String,
    val id: String,
    val title: String,
    val safeForWork: Boolean,
    val thumbnailUrl: String,
    val amount: Double?,
    val currency: String?,
    val artist: String?
)
