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
import java.util.Timer

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

                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    val timer = Timer()

    val titi = Titi()

    val wea by titi.counterFlow.collectAsState(initial = 0)

    Column {

        Text(text = wea.toString())

        Button(onClick = {
            timer.scheduleAtFixedRate(titi, 0, 1000)
        }) {
            Text(text = "Start")
        }

        Button(onClick = {
            timer.cancel()
        }) {
            Text(text = "Cancel")
        }
    }

}


