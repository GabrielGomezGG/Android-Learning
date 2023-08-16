package com.example.mobiletworeview.ui

sealed interface ResponseUiState{
    object Loading : ResponseUiState
    data class Success<T>(val response : T) : ResponseUiState
    data class Error(val message : String) : ResponseUiState
}