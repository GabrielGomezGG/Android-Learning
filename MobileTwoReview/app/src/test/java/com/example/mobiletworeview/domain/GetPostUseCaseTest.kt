package com.example.mobiletworeview.domain

import com.example.mobiletworeview.data.PostRepository
import com.example.mobiletworeview.fake.FakePostRepository
import com.example.mobiletworeview.fake.FakePostRepositoryFail
import com.example.mobiletworeview.ui.ResponseUiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class GetPostUseCaseTest{

    private lateinit var getPostUseCase: GetPostUseCase
    private lateinit var fakePostRepository: PostRepository

    @Test
    fun `when getPostUseCase is called then return a list of post`() = runTest{
        // Given
        fakePostRepository = FakePostRepository()
        getPostUseCase = GetPostUseCase(fakePostRepository)

        // When
        val result = getPostUseCase()
        val expected = ResponseUiState.Success(FakePostRepository().getPost())

        // Then
        Assert.assertEquals(result, expected)
        Assert.assertNotNull(result)
    }

    @Test
    fun `when getPostUseCase is called with a null o emptyList then return ResponseUiStateError`() = runTest{
        // Given
        fakePostRepository = FakePostRepositoryFail()
        getPostUseCase = GetPostUseCase(fakePostRepository)

        // When
        val result = getPostUseCase()
        val expected = ResponseUiState.Error("Data is empty")

        // Then
        Assert.assertEquals(result, expected)
    }

}