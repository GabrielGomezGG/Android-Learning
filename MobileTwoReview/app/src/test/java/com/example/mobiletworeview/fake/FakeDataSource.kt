package com.example.mobiletworeview.fake

import com.example.mobiletworeview.data.model.Post
import retrofit2.Response

object FakeDataSource {

    private val fakePost = listOf(
        Post(1, 1, "title1", "body1"),
        Post(2, 2, "title2", "body2"),
        Post(3, 3, "title3", "body3"),
        Post(4, 4, "title4", "body4"),
        Post(5, 5, "title5", "body5"),
    )

    val fakeResponseOk = Response.success(fakePost)!!

}