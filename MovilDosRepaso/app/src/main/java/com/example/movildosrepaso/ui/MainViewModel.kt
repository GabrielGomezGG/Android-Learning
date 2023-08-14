package com.example.movildosrepaso.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movildosrepaso.data.ApiService
import com.example.movildosrepaso.data.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiService : ApiService
) : ViewModel() {

    private val _post = MutableLiveData<List<Post>>()
    val post: LiveData<List<Post>> = _post

    init {
        getPost()
    }

    private fun getPost() {
        viewModelScope.launch {
            if(apiService.getPosts().isSuccessful){
                val po = apiService.getPosts().body()!!
                _post.value = po

            }else{
                //TODO: ERROR
            }
        }
    }
}