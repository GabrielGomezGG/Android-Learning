package com.gg.apollographqlexample.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloClient
import com.gg.apollographqlexample.CountryListQuery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MainUiState {
    data object Loading : MainUiState
    data class Success(val countries: List<CountryListQuery.Country>) : MainUiState
    data class Error(val message: String) : MainUiState
}

class MainViewModel : ViewModel() {

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/graphql")
        .build()

    private val _countries = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val countries = _countries.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            try {

                val response = apolloClient.query(CountryListQuery()).execute()
                _countries.value = MainUiState.Success(response.data?.countries.orEmpty())

            } catch (e: Exception) {
                _countries.value = MainUiState.Error(e.message ?: "An error occurred")
            }

        }
    }


}