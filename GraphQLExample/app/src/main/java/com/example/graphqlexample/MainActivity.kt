package com.example.graphqlexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.graphqlexample.ui.theme.GraphQLExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Text(text = "titi")
                    Example()
                }
            }
        }
    }
}

@Composable
fun Example() {
    LaunchedEffect(Unit) {
        val response = apolloClient.query(LaunchListQuery()).execute()
        response.data?.launches?.launches?.forEach {
            if (it != null) {
                Log.i("titi", it.site.toString())
            }
        }
    }
}
