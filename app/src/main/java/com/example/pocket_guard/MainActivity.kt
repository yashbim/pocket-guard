package com.example.pocket_guard

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)


//        Vibration Button
        val startvibration: Button = findViewById(R.id.startvibration)
        startvibration.setOnClickListener{
            vibrate_function()
        }
    }

    private fun vibrate_function() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        var time : Int = 1500;
        if (vibrator != null) {
            val vibrationEffect =
                VibrationEffect.createOneShot(time.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)

        }
    }



}