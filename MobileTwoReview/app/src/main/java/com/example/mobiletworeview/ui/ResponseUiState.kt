package com.example.mobiletworeview.ui

import androidx.lifecycle.LiveData
import com.example.mobiletworeview.data.db.entity.PostEntity

sealed interface ResponseUiState{
    object Loading : ResponseUiState
    data class Success(val response : LiveData<List<PostEntity>>) : ResponseUiState
    data class Error(val message : String = "Error") : ResponseUiState
}