package com.example.my_first_application.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import java.util.*

class GreetingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
        val actionBar = supportActionBar
        val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        val shrf: String? = sharedPreferences.getString(Constants.USER_INFO, "")
        val greetings = findViewById<Button>(R.id.buttonStart)
        val timer = Timer()
        actionBar!!.hide()
        if (shrf.isNullOrEmpty()) {
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