package com.example.mobiletworeview.ui

import androidx.lifecycle.LiveData
import com.example.mobiletworeview.data.db.entity.PostEntity

sealed interface ResponseUiState<out X>{
    object Loading : ResponseUiState<Nothing>
    data class Success<T>(val response : LiveData<T>) : ResponseUiState<T>
    data class Error(val message : String = "Error") : ResponseUiState<Nothing>
}