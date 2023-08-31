package com.example.mobiletworeview.ui

import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.domain.GetPostUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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
    fun `when call getPost them _post get posts from GetPostUseCase`() = runTest {

        val expected = listOf(
            Post(1, 1, "title", "body"),
            Post(2, 2, "title", "body"),
            Post(3, 3, "title", "body")
        )

        val expectedFlow = flow{
            emit(expected)
        }

        coEvery { postDBRepository.getPostFromDB() } returns expectedFlow

        //When
        mainViewModel.getPost()

        //Then
        val actual = getPostUseCase().first()
        Assert.assertEquals(expected, actual)
    }
}