package com.example.mobiletworeview.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.domain.GetPostUseCase
import com.example.mobiletworeview.fake.FakeDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock

class MainViewModelTest{

    private lateinit var mainViewModel: MainViewModel

    @RelaxedMockK
    private lateinit var getPostUseCase: GetPostUseCase

    @RelaxedMockK
    private lateinit var postDBRepository: PostDBRepository

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getPostUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `getPost should return success when getPostUseCase return not null`() = runTest{

        // Given
        coEvery { getPostUseCase() } returns postDBRepository.getPostFromDB()

        //val expected = ResponseUiState.Success(FakeDataSource.fakePostDB)
        val expected = ResponseUiState.Loading
        // When
        mainViewModel.getPost()

        // Then
        Assert.assertEquals(mainViewModel.post.value, expected)
    }

}