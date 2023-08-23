package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.model.PostResponse
import com.example.mobiletworeview.data.db.entity.PostEntity

fun PostResponse.toPostEntity() : PostEntity{
    return PostEntity(
        id, userId, title, body
    )
}

fun PostEntity.toPost() : Post{
    return Post(
        id, userId, title, body
    )
}