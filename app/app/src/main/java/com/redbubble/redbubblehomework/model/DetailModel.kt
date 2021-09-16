package com.redbubble.redbubblehomework.model

data class DetailModel(
    val id: String,
    val title: String,
    val artist: String,
    val avatarUrl: String,
    val description: String,
    val artistId: String,
    val userName: String,
    val imageUrl: String?,
    val workDetailSafeForWork: String,
    val shareUrl: String,
    val availableProducts: List<HomeModel>
)
