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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val postDBRepository: PostDBRepository
) : ViewModel() {

//    private val _post = MutableLiveData<ResponseUiState>(ResponseUiState.Loading)
//    val post: LiveData<ResponseUiState> = _post

    private val _posts = MutableStateFlow<ResponseUiState>(ResponseUiState.Loading)
    val posts : StateFlow<ResponseUiState> = _posts

//    init {
//        getPost()
//    }

    fun getPost() {
        viewModelScope.launch(Dispatchers.IO) {
            try{
                getPostUseCase()
                    .catch { _posts.value = ResponseUiState.Error("AAAAAAAAAAAAAA") }
                    .collect{
                        _posts.value = ResponseUiState.Success(it)
                    }
            }catch (e : Exception){
                _posts.value = ResponseUiState.Error()
            }

//            if(getPostUseCase() == null){
//                _post.postValue(ResponseUiState.Error("Error"))
//            }else{
//                _post.postValue(ResponseUiState.Success(getPostUseCase()!!))
//            }

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