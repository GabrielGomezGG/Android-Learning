package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.model.PostResponse
import com.example.mobiletworeview.data.db.entity.PostEntity

fun PostResponse.toPostEntity() : PostEntity{
    return PostEntity(
        id, userId, title, body
    )
}

fun PostResponse.toPost() : Post{
    return Post(
        id, userId, title, body
    )
}

fun PostEntity.toPost() : Post{
    return Post(
        id, userId, title, body
    )
}

fun Post.toPostEntity() : PostEntity{
    return PostEntity(
        id, userId, title, body
    )
}

fun Post.toPostResponse() : PostResponse{
    return PostResponse(
        id, userId, title, body
    )
}