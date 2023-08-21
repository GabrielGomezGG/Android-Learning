package com.example.mobiletworeview.fake

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.api.model.Post
import com.example.mobiletworeview.data.db.entity.PostEntity
import retrofit2.Response

object FakeDataSource {

    private val fakePostsDB = listOf(
        PostEntity(1, 1, "title1", "body1"),
        PostEntity(2, 2, "title2", "body2"),
        PostEntity(3, 3, "title3", "body3"),
    )

    val fakePostDB = MutableLiveData(fakePostsDB)

    private val fakePost = listOf(
        Post(1, 1, "title1", "body1"),
        Post(2, 2, "title2", "body2"),
        Post(3, 3, "title3", "body3"),
        Post(4, 4, "title4", "body4"),
        Post(5, 5, "title5", "body5"),
    )

    val fakeResponseOk = Response.success(fakePost)!!

}