package com.yourname.vitalsscreen

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.presagetech.smartspectra.SmartSpectraView
import com.presagetech.smartspectra.SmartSpectraSdk

class MainActivity : AppCompatActivity() {

    private lateinit var smartSpectraView: SmartSpectraView
    private lateinit var tvHeartRate: TextView
    private lateinit var tvBreathingRate: TextView
    private lateinit var tvStatus: TextView

    private val apiKey = "YOUR_PRESAGE_API_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHeartRate = findViewById(R.id.tv_heart_rate)
        tvBreathingRate = findViewById(R.id.tv_breathing_rate)
        tvStatus = findViewById(R.id.tv_status)
        smartSpectraView = findViewById(R.id.smart_spectra_view)

        SmartSpectraSdk.getInstance().setApiKey(apiKey)
    }

    private fun updateHeartRateUI(bpm: Int) {
        val (color, status) = when {
            bpm < 60  -> Pair(Color.parseColor("#00FFFF"), "LOW")
            bpm < 100 -> Pair(Color.parseColor("#00FF41"), "NORMAL")
            bpm < 120 -> Pair(Color.parseColor("#FFD700"), "ELEVATED")
            else      -> Pair(Color.parseColor("#FF0040"), "HIGH")
        }

        // animate background color change based on heart rate
        val rootView = findViewById<android.view.View>(android.R.id.content)
        val currentColor = (rootView.background as? android.graphics.drawable.ColorDrawable)?.color
            ?: Color.parseColor("#0a0a0f")

        ValueAnimator.ofObject(ArgbEvaluator(), currentColor, color).apply {
            duration = 800
            addUpdateListener { animator ->
                rootView.setBackgroundColor(animator.animatedValue as Int)
            }
            start()
        }

        tvHeartRate.text = "$bpm bpm"
        tvHeartRate.setTextColor(color)
        tvStatus.text = status
        tvStatus.setTextColor(color)
    }

    private fun updateBreathingRateUI(bpm: Int) {
        tvBreathingRate.text = "$bpm bpm"

        // pulse animation speed based on breathing rate
        val duration = if (bpm > 0) (60000L / bpm) else 2000L
        tvBreathingRate.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(duration / 2)
            .withEndAction {
                tvBreathingRate.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(duration / 2)
                    .start()
            }
            .start()
    }
}