package com.example.mobiletworeview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobiletworeview.data.ApiRepository
import com.example.mobiletworeview.data.ApiService
import com.example.mobiletworeview.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    //private val apiService : ApiService
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _post = MutableLiveData<ResponseUiState>(ResponseUiState.Loading)
    val post: LiveData<ResponseUiState> = _post

    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {
//            if(apiService.getPosts().isSuccessful){
//                val po = apiService.getPosts().body()!!
//                _post.value = ResponseUiState.Success(po)
//
//            }else{
//                _post.value = ResponseUiState.Error("Error")
//            }
            _post.value = ResponseUiState.Success(apiRepository.getPost())
        }
    }
}