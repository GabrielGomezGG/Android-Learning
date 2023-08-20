package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.Post
import com.example.mobiletworeview.fake.FakeApiService
import com.example.mobiletworeview.fake.FakeApiServiceFail
import com.example.mobiletworeview.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ApiPostRepositoryTest{

    private lateinit var postRepository: PostRepository
    private lateinit var fakeApiService: ApiService

    @Test
    fun getPostFromApiService() = runTest{

        fakeApiService = FakeApiService()

        postRepository = ApiPostRepository(fakeApiService)

        val expected = FakeDataSource.fakeResponseOk.body()!!

        val obtained = postRepository.getPost()

        Assert.assertEquals(expected,obtained)
    }

    @Test
    fun getEmptyListFromApiServiceWhenApiError() = runTest {

        fakeApiService = FakeApiServiceFail()

        postRepository = ApiPostRepository(fakeApiService)

        val expected = emptyList<Post>()

        val obtained = postRepository.getPost()

        Assert.assertEquals(expected,obtained)
    }

}