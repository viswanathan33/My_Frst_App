package com.example.my_first_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class GreetingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
        val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.BundleKey.share_pref,
            Context.MODE_PRIVATE)
        val sh_rf: String? =sharedPreferences.getString(Constants.BundleKey.USER_INFO,"")
        if (sh_rf=="") {
            val greetings = findViewById<Button>(R.id.buttonStart)
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    val intent = Intent(this@GreetingsActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }, 3000)
            greetings.setOnClickListener {
                timer.cancel()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}