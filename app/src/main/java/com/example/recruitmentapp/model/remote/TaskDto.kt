package com.example.recruitmentapp.model.remote

import com.google.gson.annotations.SerializedName

data class TaskDto(
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val modificationDate: String,
    val orderId: Int,
    val title: String
)