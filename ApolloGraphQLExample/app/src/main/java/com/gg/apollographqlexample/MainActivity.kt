package com.gg.apollographqlexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gg.apollographqlexample.ui.theme.ApolloGraphQLExampleTheme
import com.gg.apollographqlexample.ui.theme.MainUiState
import com.gg.apollographqlexample.ui.theme.MainViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ApolloGraphQLExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val countryList by mainViewModel.countries.collectAsState()

                    when(val countries = countryList){
                        is MainUiState.Error -> {
                            Text(text = countries.message)
                        }
                        MainUiState.Loading -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                CircularProgressIndicator()
                            }
                        }
                        is MainUiState.Success -> {
                            MainScreen(countries = countries.countries)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    countries: List<CountryListQuery.Country>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(8.dp)
    ) {
        items(countries) { country ->
            ListItem(
                headlineContent = {
                    Text(text = country.name)
                },
                supportingContent = {
                    Text(text = country.code)
                },
                trailingContent = {
                    Text(text = "+${country.phone}")
                },
                leadingContent = {
                    Text(text = country.emoji)
                }
            )
        }
    }
}