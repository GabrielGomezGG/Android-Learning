package com.example.camaraintentexample

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.camaraintentexample.data.ImageDao
import com.example.camaraintentexample.data.ImageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageDao: ImageDao
) : ViewModel() {

    private val _images = MutableStateFlow<List<ImageEntity>>(emptyList())
    val images = _images.asStateFlow()

    init {
        viewModelScope.launch {
            imageDao.getAllImages().collect{
                _images.value = it
            }
        }
    }

    fun addImage(uri: Uri) {
//        _images.value = _images.value + uri
        viewModelScope.launch {
            imageDao.insertImage(ImageEntity(uri = uri.toString()))
        }
    }

}