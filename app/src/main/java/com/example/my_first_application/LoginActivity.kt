package com.example.my_first_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private var mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private var passwordPattern = "^(?=.*[0-9])" + ".{0,}$"
    private var passwordPattern1 = "^(?=.*[a-z])" + ".{0,}$"
    private var passwordPattern2 = "^(?=.*[A-Z])" + ".{0,}$"
    private var passwordPattern3 = "^(?=.*[@#$%^&+=])" + ".{0,}$"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        actionBar!!.hide()
        val mail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val sigup = findViewById<TextView>(R.id.signup)
        val login = findViewById<Button>(R.id.log_button)
        val sharedPreferences = getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        login.setOnClickListener {
            val gson = Gson()
            val userInfo = UserInfo()
            userInfo.setEmail(mail.text.toString())
            userInfo.setpassword(password.text.toString())
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            val json: String = gson.toJson(userInfo)
            if (userInfo.getEmail()?.isEmpty()!!) {
                mail.error = Constants.mail_condition1
            } else {
                if (userInfo.getEmail()!!.trim().matches(mailPattern.toRegex())) {
                    if (userInfo.getPassword()?.isEmpty()!!) {
                        password.error = Constants.password_condition1
                    } else {
                        if (userInfo.getPassword()!!.length < 8) {
                            password.error = Constants.password_condition2
                        } else {
                            if (userInfo.getPassword()!!.trim()
                                    .matches(passwordPattern.toRegex())
                            ) {
                                if (userInfo.getPassword()!!.trim()
                                        .matches(passwordPattern1.toRegex())
                                ) {
                                    if (userInfo.getPassword()!!.trim()
                                            .matches(passwordPattern2.toRegex())
                                    ) {
                                        if (userInfo.getPassword()!!.trim()
                                                .matches(passwordPattern3.toRegex())
                                        ) {
                                            editor.putString(Constants.USER_INFO, json)
                                            editor.apply()
                                            val intent = Intent(this, HomeActivity::class.java)
                                            startActivity(intent)
                                            finish()
                                        } else password.error = Constants.password_condition3
                                    } else {
                                        password.error = Constants.password_condition4
                                    }
                                } else {
                                    password.error = Constants.password_condition5
                                }
                            } else {
                                password.error = Constants.password_condition6
                            }
                        }
                    }
                } else {
                    mail.error = Constants.mail_condition2
                }

            }
        }
        sigup.setOnClickListener {
            val intent=Intent(this,SigupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}