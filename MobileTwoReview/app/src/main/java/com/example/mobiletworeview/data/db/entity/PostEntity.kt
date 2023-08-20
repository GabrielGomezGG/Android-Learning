package com.example.mobiletworeview.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
data class PostEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int,
    val title : String,
    val body : String,
)