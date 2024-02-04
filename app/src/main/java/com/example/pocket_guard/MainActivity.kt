package com.example.pocket_guard

import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Button
import android.widget.EditText
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
            var get_interval : EditText = findViewById(R.id.interval)

            val intervalText = get_interval.text.toString()
            var intervalInt: Int
            try {
                intervalInt = intervalText.toInt()
            } catch (e: NumberFormatException) {
                // Handle the case where the text is not a valid integer
                // For example, you might display an error message to the user
                Log.e("Conversion Error", "Invalid integer value in EditText")
                intervalInt = 0 // Or set a default value as needed
            }
                Toast.makeText(applicationContext,"Time interval set to 30s", Toast.LENGTH_SHORT).show()
                vibrate(intervalInt,time)


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