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
        val actionBar = supportActionBar
        actionBar!!.hide()
        val sharedPreferences:SharedPreferences=getSharedPreferences(Constants.share_pref,
            Context.MODE_PRIVATE)
        val shrf: String? = sharedPreferences.getString(Constants.USER_INFO, "")
        if (shrf=="") {
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