package com.anatoliyvinokurov.skewbsolver

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    private val delayMillis = 2000L // Delay in milliseconds for showing the welcome screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Delay for a few seconds and then start the MainActivity
        Handler().postDelayed({
            val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
}