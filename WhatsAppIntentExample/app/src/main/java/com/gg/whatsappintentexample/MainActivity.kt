package com.gg.whatsappintentexample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gg.whatsappintentexample.ui.theme.WhatsAppIntentExampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      val context = LocalContext.current

      WhatsAppIntentExampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
          ) {
            Button(onClick = {
              sendWhatsApp(
                context,
                "1130006569"
                )
            }) {
              Text("Send WhatsApp Message")
            }
          }
        }
      }
    }
  }
}

fun sendWhatsApp(
  context: Context,
  phoneNumber: String,
) {
  context.startActivity(
    // on below line we are opening the intent.
    Intent(
      // on below line we are calling
      // uri to parse the data
      Intent.ACTION_VIEW,
      Uri.parse(
        // on below line we are passing uri,
        // message and whats app phone number.

        "https://api.whatsapp.com/send?phone=+54$phoneNumber&text=",

        )
    )
  )

}
