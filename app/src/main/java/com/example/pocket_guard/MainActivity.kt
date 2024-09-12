package com.example.pocket_guard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isVibrating = false
    private val interval: Long = 10000 // 10 seconds in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        val startVibrationButton: Button = findViewById(R.id.startvibration)
        startVibrationButton.setOnClickListener {
            if (!isVibrating) {
                isVibrating = true
                Toast.makeText(applicationContext, "Vibrating every " + interval / 1000 + " seconds", Toast.LENGTH_SHORT).show()
                startVibrationService()
            } else {
                isVibrating = false
                stopVibrationService()
                Toast.makeText(applicationContext, "Vibration stopped", Toast.LENGTH_SHORT).show()
            }

            // Update button text
            startVibrationButton.text = if (isVibrating) "Stop" else "Start"
        }
    }

    private fun startVibrationService() {
        val intent = Intent(this, VibrationService::class.java)
        startForegroundService(intent) // Use startForegroundService to ensure it's a foreground service
    }

    private fun stopVibrationService() {
        val intent = Intent(this, VibrationService::class.java)
        stopService(intent)
    }
}
