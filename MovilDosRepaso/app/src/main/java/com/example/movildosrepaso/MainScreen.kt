package com.example.movildosrepaso

import android.graphics.drawable.shapes.Shape
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.movildosrepaso.data.model.Post
import com.example.movildosrepaso.ui.MainViewModel

@Composable
fun MainScreen(mainViewModel : MainViewModel) {

    val post by mainViewModel.post.observeAsState(emptyList())

    LazyColumn(Modifier.fillMaxSize().padding(vertical = 0.dp)) {
        items(post){
            PostView(it)
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