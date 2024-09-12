package com.example.pocket_guard

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.app.NotificationCompat

class VibrationService : Service() {

    private lateinit var vibrator: Vibrator
    private lateinit var handler: Handler
    private val time: Int = 750 // Vibration duration in milliseconds
    private val interval: Long = 10000 // 10 seconds in milliseconds
    private val channelId = "VibrationServiceChannel"

    override fun onCreate() {
        super.onCreate()
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        handler = Handler(Looper.getMainLooper())

        // Create a notification channel for Android O and above
        createNotificationChannel()

        // Start the service in the foreground with a notification
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Pocket Guard")
            .setContentText("Vibrating every ${interval / 1000} seconds")
            .setSmallIcon(R.drawable.pocketguardlogo) // Replace with your own icon
            .build()

        startForeground(1, notification)

        // Start vibrating periodically
        startVibrating()
    }

    private fun startVibrating() {
        vibrate(time)
        handler.postDelayed(object : Runnable {
            override fun run() {
                vibrate(time)
                handler.postDelayed(this, interval)
            }
        }, interval)
    }

    private fun vibrate(time: Int) {
        val vibrationEffect = VibrationEffect.createOneShot(time.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }

    private fun createNotificationChannel() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                "Vibration Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null) // Stop vibration
    }
}
