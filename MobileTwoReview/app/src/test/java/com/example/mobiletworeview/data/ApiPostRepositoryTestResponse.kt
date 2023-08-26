package com.example.mobiletworeview.data

import com.example.mobiletworeview.data.api.ApiService
import com.example.mobiletworeview.data.api.model.PostResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiPostRepositoryTestResponse{

    @RelaxedMockK
    private lateinit var apiService: ApiService

    private lateinit var postRepository: PostRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        postRepository = ApiPostRepository(apiService)
    }

    @Test
    fun getPostFromApiService() = runTest {

        val fakePost = listOf(
            PostResponse(1,1,"title1","body1"),
            PostResponse(2,2,"title2","body2"),
            PostResponse(3,3,"title3","body3")
        )

        //Given
        coEvery { apiService.getPosts().body() } returns fakePost


        //When
        val actual = postRepository.getPost()

        //Then
        Assert.assertEquals(actual, fakePost)
        Assert.assertEquals(actual?.first()?.id, 1)
        Assert.assertEquals(actual?.size, 3)

    }

//    @Test
//    fun getPostFromApiService() = runTest{
//
//        fakeApiService = FakeApiService()
//
//        postRepository = ApiPostRepository(fakeApiService)
//
//        val expected = FakeDataSource.fakeResponseOk.body()!!
//
//        val obtained = postRepository.getPost()
//
//        Assert.assertEquals(expected,obtained)
//    }
//
//    @Test
//    fun getEmptyListFromApiServiceWhenApiError() = runTest {
//
//        fakeApiService = FakeApiServiceFail()
//
//        postRepository = ApiPostRepository(fakeApiService)
//
//        val expected = null
//
//        val obtained = postRepository.getPost()
//
//        Assert.assertEquals(expected,obtained)
//    }

}