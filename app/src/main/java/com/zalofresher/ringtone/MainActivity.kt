package com.zalofresher.ringtone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton


class MainActivity : AppCompatActivity(), View.OnClickListener {

    // declaring objects of Button class
    private lateinit var btnStartBackground: MaterialButton
    private lateinit var btnStartForeground: MaterialButton
    private lateinit var tvBackground: TextView
    private lateinit var tvForeground: TextView

    private var foregroundRunning = false
    private var backgroundRunning = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartBackground = findViewById<View>(R.id.btnStartBackground) as MaterialButton
        btnStartForeground = findViewById<View>(R.id.btnStartForeground) as MaterialButton
        tvBackground = findViewById(R.id.tvBackground)
        tvForeground = findViewById(R.id.tvForeground)

        btnStartBackground.setOnClickListener(this)
        btnStartForeground.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view) {
            btnStartBackground -> {
                if (!backgroundRunning) {
                    backgroundRunning = true
                    btnStartBackground.text = getText(R.string.stopBackgroundButtonText)
                    btnStartBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.red
                        )
                    )
                    startService(Intent(this, RingBackgroundService::class.java))
                    Toast.makeText(
                        applicationContext,
                        "Background: startService() is called",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Disable foreground button
                    btnStartForeground.visibility = View.GONE
                    tvForeground.visibility = View.GONE

                } else {
                    backgroundRunning = false
                    btnStartBackground.text = getText(R.string.startBackgroundButtonText)
                    btnStartBackground.setBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.blue
                        )
                    )
                    stopService(
                        Intent(this, RingBackgroundService::class.java)
                    )
                    Toast.makeText(
                        applicationContext,
                        "Background: stopService() is called",
                        Toast.LENGTH_SHORT
                    ).show()
                    btnStartForeground.visibility = View.VISIBLE
                    tvForeground.visibility = View.VISIBLE
                }
            }
            btnStartForeground -> {
                if (!foregroundRunning) {
                    foregroundRunning = true
                    btnStartForeground.text = getText(R.string.stopForegroundButtonText)
                    btnStartForeground.setBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.red
                        )
                    )
                    startForegroundService(Intent(this, RingForegroundService::class.java))
                    Toast.makeText(
                        applicationContext,
                        "Foreground: startForegroundService() is called",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Disable background button
                    btnStartBackground.visibility = View.GONE
                    tvBackground.visibility = View.GONE

                } else {
                    foregroundRunning = false
                    btnStartForeground.text = getText(R.string.startForegroundButtonText)
                    btnStartForeground.setBackgroundColor(
                        ContextCompat.getColor(
                            applicationContext,
                            R.color.blue
                        )
                    )
                    stopService(
                        Intent(this, RingForegroundService::class.java)
                    )
                    Toast.makeText(
                        applicationContext,
                        "Foreground: stopService() is called",
                        Toast.LENGTH_SHORT
                    ).show()
                    btnStartBackground.visibility = View.VISIBLE
                    tvBackground.visibility = View.VISIBLE
                }
            }
        }
    }
}
