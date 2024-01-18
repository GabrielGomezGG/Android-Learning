package com.example.camaraintentexample.data

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val uri: String
)
