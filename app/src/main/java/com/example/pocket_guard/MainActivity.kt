package com.example.pocket_guard

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var vibrator: Vibrator
    private var isVibrating = false
    private val time: Int = 750 // Vibration duration in milliseconds
    private val interval: Long = 10000 // 30 seconds in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        // Initialize vibrator and handler
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        handler = Handler(Looper.getMainLooper())

        val startVibrationButton: Button = findViewById(R.id.startvibration)
        startVibrationButton.setOnClickListener {
            if (!isVibrating) {
                isVibrating = true
                Toast.makeText(applicationContext, "Vibrating every 30 seconds", Toast.LENGTH_SHORT).show()
                startVibrating()
            } else {
                isVibrating = false
                handler.removeCallbacksAndMessages(null)
                Toast.makeText(applicationContext, "Vibration stopped", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun startVibrating() {
        // Vibrate once, then post a delayed task to vibrate again after the interval
        vibrate(time)
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (isVibrating) {
                    vibrate(time)
                    handler.postDelayed(this, interval) // Repeat every 30 seconds
                }
            }
        }, interval)
    }

    private fun vibrate(time: Int) {
        val vibrationEffect = VibrationEffect.createOneShot(time.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }
}
