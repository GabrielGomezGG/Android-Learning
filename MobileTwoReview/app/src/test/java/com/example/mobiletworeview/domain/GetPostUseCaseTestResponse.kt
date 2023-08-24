package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.fake.FakeDataSource
import com.example.mobiletworeview.fake.FakePostDBRepository
import com.example.mobiletworeview.fake.FakePostRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPostUseCaseTestResponse{

    private lateinit var getPostUseCase: GetPostUseCase

    private lateinit var fakePostRepository: PostRepository
    private lateinit var fakePostDBRepository: PostDBRepository

    @Before
    fun setUp(){
        fakePostRepository = FakePostRepository()
        fakePostDBRepository = FakePostDBRepository()

        getPostUseCase = GetPostUseCase(fakePostRepository, fakePostDBRepository)
    }

    @Test
    fun `when getPostUseCase is called then return a list of post`() = runTest{
        // Given

        // When
        var result : List<Post> = emptyList()
        getPostUseCase().onEach { result = it }


        // Then
        val expected = FakeDataSource.fakePostDB

        Assert.assertEquals(expected, result)
        //Assert.assertNotNull(result)
    }

    @Test
    fun `when getPostUseCase is called with a null o emptyList then return ResponseUiStateError`() = runTest{
        // Given

//        fakePostRepository = FakePostRepositoryFail()
//        fakePostDBRepository = FakePostDBRepositoryFail()
//
//        getPostUseCase = GetPostUseCase(fakePostRepository, fakePostDBRepository)
//
//        // When
//        val result = getPostUseCase()?.value
//
//        // Then
//        val expected = null
//
//        Assert.assertEquals(result, expected)
    }

}