package com.example.timerexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.timerexample.ui.theme.TimerExampleTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            TimerExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val contador by mainViewModel.count.collectAsState(initial = 0)
                    val activo by mainViewModel.active.collectAsState(initial = true)
                    MainScreen(contador,activo) { mainViewModel.stopFlow() }
                }
            }
        }
    }
}

@Composable
fun MainScreen(titi: Int, activo: Boolean, onStop: () -> Unit) {

    Column {
        Text(text = titi.toString())
        Text(text = activo.toString())
        Button(onClick = { onStop() }) {
            Text(text = "Stop")
        }
    }

}


