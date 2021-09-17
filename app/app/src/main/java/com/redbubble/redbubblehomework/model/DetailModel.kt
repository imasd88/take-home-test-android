package com.redbubble.redbubblehomework.model

data class DetailModel(
    val id: String,
    val title: String = "default",
    val avatarUrl: String,
    val description: String = "defaultdescription",
    val artistId: String,
    val userName: String,
    val imageUrl: String,
    val safeForWork: String,
    val shareUrl: String,
    val availableProducts: List<HomeModel>
)
