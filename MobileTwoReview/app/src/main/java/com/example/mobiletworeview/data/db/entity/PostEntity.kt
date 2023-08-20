package com.example.mobiletworeview.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_entity")
data class PostEntity(

    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title : String,
    val body : String,
)