package com.gg.googlesheetapiexample.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.googlesheetapiexample.data.ApiRepository
import com.gg.googlesheetapiexample.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _products = MutableStateFlow(emptyList<Product>())
    val products = _products.asStateFlow()

    init {
        getSheetData()
    }

    private fun getSheetData() {
        viewModelScope.launch {
            _products.value = apiRepository.getSheetData()
        }
    }

}