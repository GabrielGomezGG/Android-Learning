package com.example.mobiletworeview.fake

import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.api.model.PostResponse
import com.example.mobiletworeview.data.db.entity.PostEntity
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object FakeDataSource {

    val fakePostDB = flow {
        emit(
            listOf(
                Post(1, 1, "title1", "body1"),
                Post(2, 2, "title2", "body2"),
                Post(3, 3, "title3", "body3"),
            )
        )
    }

    val fakeAllPost = listOf(
        PostEntity(1, 1, "title1", "body1"),
        PostEntity(2, 2, "title2", "body2"),
        PostEntity(3, 3, "title3", "body3"),
    )

    private val fakePostResponses = listOf(
        PostResponse(1, 1, "title1", "body1"),
        PostResponse(2, 2, "title2", "body2"),
        PostResponse(3, 3, "title3", "body3"),
        PostResponse(4, 4, "title4", "body4"),
        PostResponse(5, 5, "title5", "body5"),
    )

    val fakeResponseOk = Response.success(fakePostResponses)!!

}