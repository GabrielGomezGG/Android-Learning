package com.example.mobiletworeview.ui

import app.cash.turbine.test
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.toPostEntity
import com.example.mobiletworeview.data.toPostResponse
import com.example.mobiletworeview.domain.GetPostUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var getPostUseCase: GetPostUseCase

    @RelaxedMockK
    private lateinit var postRepository: PostRepository

    @RelaxedMockK
    private lateinit var postDBRepository: PostDBRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getPostUseCase = GetPostUseCase(postRepository, postDBRepository)
        mainViewModel = MainViewModel(getPostUseCase, postDBRepository)
    }

    @Test
    fun `when init them _post get posts from GetPostUseCase`() = runTest {

        val expected = listOf(
            Post(1, 1, "title", "body"),
            Post(2, 2, "title", "body"),
            Post(3, 3, "title", "body")
        )

        //Given
        coEvery { postDBRepository.getAllPost() } returns expected.map { it.toPostEntity() }
        coEvery { postRepository.getPost() } returns expected.map { it.toPostResponse() }

        //When
        mainViewModel.getPost()

        //Then
        val useCase = getPostUseCase().toList()
        Assert.assertEquals(expected, useCase)


//        mainViewModel.posts.test {
//            val actual = awaitItem()
//            Assert.assertEquals(ResponseUiState.Success(expected), actual)
//        }

    }
}