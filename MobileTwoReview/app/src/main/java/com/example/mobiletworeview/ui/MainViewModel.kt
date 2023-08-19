package com.example.mobiletworeview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletworeview.domain.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val apiRepository: PostRepository
    private val getPostUseCase: GetPostUseCase
) : ViewModel() {

    private val _post = MutableLiveData<ResponseUiState>(ResponseUiState.Loading)
    val post: LiveData<ResponseUiState> = _post

    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {
            //_post.value = ResponseUiState.Success(apiRepository.getPost())
            _post.value = getPostUseCase()
        }
    }
}