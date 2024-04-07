package com.example.pocket_guard

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        var time : Int = 750;


//        Vibration Button
        val startvibration: Button = findViewById(R.id.startvibration)
        startvibration.setOnClickListener{

            val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
            var interval : Int = 30000
            var intervalInt: Int
                Toast.makeText(applicationContext,"Time interval set to 30s", Toast.LENGTH_SHORT).show()
//                vibrate(intervalInt,time)


        }
    }

    private fun vibrate(interval: Int, time: Int) {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator != null) {
            val vibrationEffect =
                VibrationEffect.createOneShot(time.toLong(), VibrationEffect.DEFAULT_AMPLITUDE)
            vibrator.vibrate(vibrationEffect)

        }
    }


}