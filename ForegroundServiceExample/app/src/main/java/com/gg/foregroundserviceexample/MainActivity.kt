package com.gg.foregroundserviceexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.gg.foregroundserviceexample.ui.theme.ForegroundServiceExampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      ForegroundServiceExampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


          val context = LocalContext.current

          val randomsTexts = listOf("AAAAAAAA", "BBBBBBBB", "CCCCCCCC", "DDDDDDDD", "EEEEEEEE")

          Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
              .fillMaxSize()
              .padding(innerPadding)
          ) {
            Button(onClick = {
              ServiceExample.startService(context, randomsTexts.random())
            }) {
              Text("Start Service")
            }
            Button(onClick = {
              ServiceExample.stopService(context)
            }) {
              Text("Stop Service")
            }
          }
        }
      }
    }
  }
}