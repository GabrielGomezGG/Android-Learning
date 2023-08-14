package com.example.movildosrepaso.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movildosrepaso.data.ApiRetrofit
import com.example.movildosrepaso.data.model.Post
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _post = MutableLiveData<List<Post>>()
    val post: LiveData<List<Post>> = _post

    init {
        getPost()
    }

    fun getPost() {

        var po: List<Post>

        viewModelScope.launch {
            if(ApiRetrofit.getApi.getPosts().isSuccessful){
                po = ApiRetrofit.getApi.getPosts().body()!!
                _post.value = po

            }else{
                Log.e("titi", "FAIL")
            }
        }
    }
}