package com.example.mobiletworeview.domain

import androidx.lifecycle.MutableLiveData
import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.fake.FakeDataSource
import com.example.mobiletworeview.fake.FakePostDBRepository
import com.example.mobiletworeview.fake.FakePostDBRepositoryFail
import com.example.mobiletworeview.fake.FakePostRepository
import com.example.mobiletworeview.fake.FakePostRepositoryFail
import com.example.mobiletworeview.ui.ResponseUiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetPostUseCaseTest{

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
        val result = getPostUseCase()?.value!!


        // Then
        val expected = FakeDataSource.fakePostDB.value!!

        Assert.assertEquals(result, expected)
        Assert.assertNotNull(result)
    }

    @Test
    fun `when getPostUseCase is called with a null o emptyList then return ResponseUiStateError`() = runTest{
        // Given

        fakePostRepository = FakePostRepositoryFail()
        fakePostDBRepository = FakePostDBRepositoryFail()

        getPostUseCase = GetPostUseCase(fakePostRepository, fakePostDBRepository)

        // When
        val result = getPostUseCase()?.value

        // Then
        val expected = null

        Assert.assertEquals(result, expected)
    }

}