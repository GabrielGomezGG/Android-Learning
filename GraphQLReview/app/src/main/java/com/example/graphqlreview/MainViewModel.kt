package com.example.graphqlreview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel(){

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList : Flow<List<Country>> = _countryList.asStateFlow()

    init {
        viewModelScope.launch {
            val response = ApolloObject.apolloClient.query(CountriesQuery()).execute()
            val algo = response.data!!.countries.map { it.toCountry() }
            _countryList.value = algo
        }
    }
}