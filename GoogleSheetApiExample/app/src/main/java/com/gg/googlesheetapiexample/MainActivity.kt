package com.gg.googlesheetapiexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gg.googlesheetapiexample.ui.theme.GoogleSheetApiExampleTheme
import com.gg.googlesheetapiexample.ui.theme.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            GoogleSheetApiExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val products by mainViewModel.products.collectAsState()

                    LazyColumn(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(products) { product ->
                            ListItem(
                                headlineContent = {
                                    Text(text = product.nombre)
                                },
                                supportingContent = {
                                    Text(text = product.username)
                                },
                                trailingContent = {
                                    Text(text = product.precio.toString())
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}