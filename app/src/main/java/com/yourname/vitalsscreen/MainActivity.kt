package com.yourname.vitalsscreen

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.presagetech.smartspectra.SmartSpectraSdk
import com.presagetech.smartspectra.SmartSpectraView

class MainActivity : AppCompatActivity() {

    private lateinit var smartSpectraView: SmartSpectraView
    private lateinit var tvHeartRate: TextView
    private lateinit var tvBreathingRate: TextView
    private lateinit var tvStatus: TextView
    private lateinit var rootLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHeartRate = findViewById(R.id.tv_heart_rate)
        tvBreathingRate = findViewById(R.id.tv_breathing_rate)
        tvStatus = findViewById(R.id.tv_status)
        rootLayout = findViewById(R.id.root_layout)
        smartSpectraView = findViewById(R.id.smart_spectra_view)

        SmartSpectraSdk.getInstance().setApiKey("Api-key")
    }
}