package com.example.recruitmentapp.ui.recruitment.list.model

import org.joda.time.DateTime

data class Task(
    val description: String,
    val imageUrl: String,
    val modificationDate: DateTime?,
    val orderId: Int,
    val title: String,
    val descriptionUrl: String
)