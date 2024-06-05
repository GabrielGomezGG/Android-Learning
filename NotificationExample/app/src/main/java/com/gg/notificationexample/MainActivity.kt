package com.gg.notificationexample

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
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
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.gg.notificationexample.ui.theme.NotificationExampleTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {

      val context = LocalContext.current

      createNotificationChannel(context)

      NotificationExampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

          Box(
              modifier = Modifier.fillMaxSize(),
              contentAlignment = Alignment.Center
              ) {
              Button(onClick = {
                setNotification(context)
              }) {
                Text("Show Notification")
              }
          }

        }
      }
    }
  }
}

@SuppressLint("MissingPermission", "NotificationPermission")
fun setNotification(
  context: Context,
){

  val intent = Intent(context, MainActivity::class.java).apply {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
  }
  val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

  var builder = NotificationCompat.Builder(context, "titi")
    .setSmallIcon(R.drawable.ic_launcher_foreground)
    .setContentTitle("titi title")
    .setContentText("titi content")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    .setContentIntent(pendingIntent)
    .setAutoCancel(true)

//  with(NotificationManagerCompat.from(context)){
//    notify(1, builder.build())
//  }
  NotificationManagerCompat.from(context).notify(1, builder.build())
}

private fun createNotificationChannel(
  context: Context
) {
  // Create the NotificationChannel, but only on API 26+ because
  // the NotificationChannel class is not in the Support Library.
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    val name = "titi channel"
    val descriptionText = "Titi description text"
    val importance = NotificationManager.IMPORTANCE_DEFAULT
    val channel = NotificationChannel("titi", name, importance).apply {
      description = descriptionText
    }
    // Register the channel with the system.
    val notificationManager: NotificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
  }
}