package com.example.recruitmentapp.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Tasks")
data class TaskEntity @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "orderId") var orderId: Int = 0,
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "imageUrl") var imageUrl: String = "",
    @ColumnInfo(name = "modificationDate") var modificationDate: String = "",
    @ColumnInfo(name = "title") var title: String = ""
) : Serializable
