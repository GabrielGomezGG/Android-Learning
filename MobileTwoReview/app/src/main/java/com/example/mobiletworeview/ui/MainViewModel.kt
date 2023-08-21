package com.example.mobiletworeview.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletworeview.data.db.PostDBRepository
import com.example.mobiletworeview.data.db.entity.PostEntity
import com.example.mobiletworeview.domain.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val postDBRepository: PostDBRepository
) : ViewModel() {

    private val _post = MutableLiveData<ResponseUiState>(ResponseUiState.Loading)
    val post: LiveData<ResponseUiState> = _post

    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {

            if(getPostUseCase() == null){
                _post.value = ResponseUiState.Error("Error")
            }else{
                _post.value = ResponseUiState.Success(getPostUseCase()!!)
            }

        }
    }

    fun deletePost(){
        viewModelScope.launch {
            postDBRepository.deletePosts()
        }
    }

    fun setPost(){
        viewModelScope.launch {
            postDBRepository.addPost(PostEntity(userId = 1, title = "title", body = "body"))
        }
    }
}