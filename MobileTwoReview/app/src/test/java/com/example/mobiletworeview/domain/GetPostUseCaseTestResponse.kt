package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.toPost
import com.example.mobiletworeview.data.toPostEntity
import com.example.mobiletworeview.data.toPostResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPostUseCaseTestResponse{

    private lateinit var getPostUseCase: GetPostUseCase

    @RelaxedMockK
    private lateinit var postRepository: PostRepository

    @RelaxedMockK
    private lateinit var postDBRepository: PostDBRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        getPostUseCase = GetPostUseCase(postRepository, postDBRepository)
    }

    @Test
    fun `when getPostUseCase is called then return a list of post`() = runTest{
        //Given
        val expected = listOf(
            Post(1, 1, "title", "body"),
            Post(2, 2, "title", "body"),
            Post(3, 3, "title", "body")
        )

        val expectedFlow = flow{
            emit(expected)
        }

//        coEvery { postDBRepository.getAllPost() } returns emptyList()//expected.map { it.toPostEntity()}
//        coEvery { postRepository.getPost() } returns expected.map { it.toPostResponse() }
        coEvery { postDBRepository.getPostFromDB() } returns expectedFlow

        //advanceUntilIdle()


        //When
        val algo = getPostUseCase().first()

        //Then
        Assert.assertEquals(expected, algo)
    }

}