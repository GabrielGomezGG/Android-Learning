package com.example.mobiletworeview.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.entity.PostEntity
import com.example.mobiletworeview.domain.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val postDBRepository: PostDBRepository
) : ViewModel() {

    private val _posts = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val posts: StateFlow<ResponseUiState> = _posts.asStateFlow()

    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            getPostUseCase()
                .catch { _posts.value = ResponseUiState.Error(it.message.toString()) }
                .collect {
                    _posts.value = ResponseUiState.Success(it)
                }

        }
    }

    fun deletePost() {
        viewModelScope.launch {
            postDBRepository.deletePosts()
        }
    }

    fun setPost() {
        viewModelScope.launch {
            postDBRepository.addPost(PostEntity(userId = 1, title = "title", body = "body"))
        }
    }
}