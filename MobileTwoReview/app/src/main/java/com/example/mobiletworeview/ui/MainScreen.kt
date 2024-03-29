package com.example.mobiletworeview.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mobiletworeview.data.Post

@Composable
fun MainScreen(mainViewModel : MainViewModel) {

    LaunchedEffect(Unit){
        Log.i("titi", "asdasdsadsad")
        mainViewModel.getPost()
    }

    val postResponse by mainViewModel.posts.collectAsState()

    when(postResponse){
        is ResponseUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(alignment = Alignment.Center))
            }
        }
        is ResponseUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (postResponse as ResponseUiState.Error).message)

            }
        }
        is ResponseUiState.Success -> {

            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 0.dp)) {
                item {
                    Button(onClick = { mainViewModel.setPost() }) {
                        Text(text = "SET")
                    }
                }
                item {
                    Button(onClick = { mainViewModel.deletePost() }) {
                        Text(text = "DELETE")
                    }
                }
                items((postResponse as ResponseUiState.Success).response){
                    PostView(it)
                }
            }
        }
    }


}

@Composable
fun PostView(post : Post) {
    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = post.title,
                Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = post.title,
                Modifier
                    .fillMaxWidth(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Start
            )
        }
    }
}