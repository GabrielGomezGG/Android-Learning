package com.gg.googlesheetapiexample.data

import com.gg.googlesheetapiexample.data.model.Product
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getSheetData(): List<Product> {
        return apiService.getSheetData().body() ?: emptyList()
    }

}