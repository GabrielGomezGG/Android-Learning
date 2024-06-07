package com.gg.foregroundserviceexample

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class ServiceExample : Service() {

  private lateinit var notificationManager: NotificationManager

  private var isServiceStarted = false

  override fun onCreate() {
    super.onCreate()

    notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
  }

  override fun onDestroy() {
    super.onDestroy()
    isServiceStarted = false
  }

  override fun onBind(p0: Intent?): IBinder? {
    return null
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

    if (!isServiceStarted) {
      makeForeground()
      isServiceStarted = true
    }

    return START_STICKY
  }

  private fun makeForeground() {
    createChannel()

    val notification = NotificationCompat.Builder(this, CHANNEL_ID)
      .setContentTitle("Foreground Service Example")
      .setContentText("This is a foreground service example")
      .setSmallIcon(R.drawable.ic_launcher_foreground)
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setCategory(NotificationCompat.CATEGORY_MESSAGE)
      .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
      .build()

    startForeground(1, notification)
  }

  private fun createChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_HIGH
      )
      notificationManager.createNotificationChannel(channel)
    }
  }

  companion object {
    const val CHANNEL_ID = "ForegroundServiceExample"
    const val CHANNEL_NAME = "Foreground Service Example"

    fun startService(context: Context) {
      val startIntent = Intent(context, ServiceExample::class.java)

      if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context.startForegroundService(startIntent)
      } else {
        context.startService(startIntent)
      }
    }

    fun stopService(context: Context) {
      val stopIntent = Intent(context, ServiceExample::class.java)
      context.stopService(stopIntent)
    }

  }

}