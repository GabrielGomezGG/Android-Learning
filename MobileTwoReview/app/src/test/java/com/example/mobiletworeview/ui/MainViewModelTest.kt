package com.example.mobiletworeview.ui

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.mobiletworeview.data.Post
import com.example.mobiletworeview.data.api.model.PostResponse
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.domain.GetPostUseCase
import com.example.mobiletworeview.fake.FakeDataSource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import retrofit2.Response

class MainViewModelTest{

    private lateinit var mainViewModel: MainViewModel

    @RelaxedMockK
    private lateinit var getPostUseCase: GetPostUseCase

    @RelaxedMockK
    private lateinit var postDBRepository: PostDBRepository

//    @get:Rule
//    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getPostUseCase,postDBRepository)
        //Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown(){
        //Dispatchers.resetMain()
    }

    @Test
    fun `getPost should return success when getPostUseCase return not null`() = runTest {

        val fakePost = listOf(
            Post(1,1,"title1","body1"),
            Post(2,2,"title2","body2"),
            Post(3,3,"title3","body3")
        )
        val fakeFlow = flow {emit(fakePost)}

        // Given
        coEvery { getPostUseCase() } returns fakeFlow

        val algo = getPostUseCase()

        algo.onEach { Log.i("titi", it.toString()) }


        // When
        mainViewModel.getPost()

        // Then
        val actual = when(val wea =  mainViewModel.posts.value){
            is ResponseUiState.Error -> null
            ResponseUiState.Loading -> fakePost
            is ResponseUiState.Success -> wea.response
        }
        Assert.assertEquals(actual, fakePost)
    }

}