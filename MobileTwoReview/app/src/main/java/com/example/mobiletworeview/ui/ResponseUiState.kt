package com.example.mobiletworeview.ui

import com.example.mobiletworeview.data.Post

sealed interface ResponseUiState{
    object Loading : ResponseUiState
    data class Success(val response : List<Post>) : ResponseUiState
    data class Error(val message : String) : ResponseUiState
}