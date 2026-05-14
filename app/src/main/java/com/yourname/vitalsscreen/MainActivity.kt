package com.yourname.vitalsscreen

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.presagetech.smartspectra.SmartSpectraView
import com.presagetech.smartspectra.SmartSpectraSdk

class MainActivity : AppCompatActivity() {

    private lateinit var smartSpectraView: SmartSpectraView
    private lateinit var tvHeartRate: TextView
    private lateinit var tvBreathingRate: TextView

    private val apiKey = "YOUR_PRESAGE_API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHeartRate = findViewById(R.id.tv_heart_rate)
        tvBreathingRate = findViewById(R.id.tv_breathing_rate)
        smartSpectraView = findViewById(R.id.smart_spectra_view)

        SmartSpectraSdk.getInstance().setApiKey(apiKey)
    }
}