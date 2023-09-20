package com.example.graphqlreview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository : MainRepository
) : ViewModel(){

    private val _countryList = MutableStateFlow<List<Country>>(emptyList())
    val countryList : Flow<List<Country>> = _countryList.asStateFlow()

    init {
        viewModelScope.launch {
//            val response = ApolloObject.apolloClient.query(CountriesQuery()).execute()
//            val algo = response.data!!.countries.map { it.toCountry() }
            _countryList.value = mainRepository.getCountries()
        }
    }
}