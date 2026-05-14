package com.yourname.vitalsscreen

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.presagetech.smartspectra.SmartSpectraSdk
import com.presagetech.smartspectra.SmartSpectraView
import com.presagetech.smartspectra.proto.MetricsProto

class MainActivity : AppCompatActivity() {

    private lateinit var smartSpectraView: SmartSpectraView
    private lateinit var tvHeartRate: TextView
    private lateinit var tvBreathingRate: TextView
    private lateinit var tvStatus: TextView
    private lateinit var rootLayout: LinearLayout

    private var lastBgColor = Color.parseColor("#0a0a0f")

    private val smartSpectraSdk: SmartSpectraSdk = SmartSpectraSdk.shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHeartRate = findViewById(R.id.tv_heart_rate)
        tvBreathingRate = findViewById(R.id.tv_breathing_rate)
        tvStatus = findViewById(R.id.tv_status)
        rootLayout = findViewById(R.id.root_layout)
        smartSpectraView = findViewById(R.id.smart_spectra_view)

        smartSpectraSdk.config.apiKey = "YOUR_PRESAGE_API_KEY"

        // observe live metrics and drive the UI
        smartSpectraSdk.metrics.observe(this) { metrics ->
            metrics?.let { handleMetrics(it) }
        }
    }

    private fun handleMetrics(metrics: MetricsProto.Metrics) {
        // get latest breathing rate
        if (metrics.hasBreathing()) {
            val breathingRate = metrics.breathing.rateList.lastOrNull()?.value?.toInt()
            breathingRate?.let { runOnUiThread { updateBreathingUI(it) } }
        }

        // get latest pulse rate from cardio
        if (metrics.hasCardio()) {
            val pulseRate = metrics.cardio.pulseRateList.lastOrNull()?.value?.toInt()
            pulseRate?.let { runOnUiThread { updateHeartRateUI(it) } }
        }
    }

    private fun updateHeartRateUI(bpm: Int) {
        val (color, status) = when {
            bpm < 60  -> Pair(Color.parseColor("#00FFFF"), "LOW ↓")
            bpm < 100 -> Pair(Color.parseColor("#00FF41"), "NORMAL ✓")
            bpm < 120 -> Pair(Color.parseColor("#FFD700"), "ELEVATED ⚠")
            else      -> Pair(Color.parseColor("#FF0040"), "HIGH !")
        }

        // animate background color driven by heart rate
        ValueAnimator.ofObject(ArgbEvaluator(), lastBgColor, color).apply {
            duration = 1000
            addUpdateListener { animator ->
                val c = animator.animatedValue as Int
                rootLayout.setBackgroundColor(
                    Color.argb(255,
                        (Color.red(c) * 0.12).toInt().coerceAtMost(25),
                        (Color.green(c) * 0.12).toInt().coerceAtMost(25),
                        (Color.blue(c) * 0.12).toInt().coerceAtMost(35)
                    )
                )
            }
            start()
        }
        lastBgColor = color

        tvHeartRate.text = "$bpm"
        tvHeartRate.setTextColor(color)
        tvStatus.text = status
        tvStatus.setTextColor(color)
    }

    private fun updateBreathingUI(bpm: Int) {
        tvBreathingRate.text = "$bpm"

        // animate scale speed driven by breathing rate
        val duration = if (bpm > 0) (60000L / bpm) else 2000L
        tvBreathingRate.animate()
            .scaleX(1.3f)
            .scaleY(1.3f)
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