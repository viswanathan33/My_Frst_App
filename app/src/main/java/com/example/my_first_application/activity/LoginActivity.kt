package com.example.my_first_application.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.model.UserInfo
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        val mail = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val sigup = findViewById<TextView>(R.id.signup)
        val login = findViewById<Button>(R.id.log_button)
        val sharedPreferences = getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        actionBar!!.hide()
        login.setOnClickListener {
            val gson = Gson()
            val userInfo = UserInfo()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            val json: String = gson.toJson(userInfo)
            userInfo.setEmail(mail.text.toString())
            userInfo.setpassword(password.text.toString())
            if (userInfo.getEmail()?.isEmpty()!!)
            {
                mail.error = getString(R.string.mailEmpty)
            }
            else if (userInfo.getEmail()!!.trim().matches(Constants.mailPattern.toRegex()))
            {
                if (userInfo.getPassword()?.isEmpty()!!)
                {
                    password.error =getString(R.string.passwordEmpty)
                }
                else if (userInfo.getPassword()!!.length < 8) {
                    password.error = getString(R.string.passwordLength)
                }
                else if (userInfo.getPassword()!!.trim().matches(Constants.passwordPattern.toRegex()))
                {
                    if (userInfo.getPassword()!!.trim().matches(Constants.passwordPattern1.toRegex()))
                    {
                        if (userInfo.getPassword()!!.trim().matches(Constants.passwordPattern2.toRegex()))
                        {
                            if (userInfo.getPassword()!!.trim().matches(Constants.passwordPattern3.toRegex()))
                            {
                                editor.putString(Constants.USER_INFO, json)
                                editor.apply()
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else
                            {
                                password.error = getString(R.string.passwordSplChar)
                            }
                        }
                        else
                        {
                            password.error = getString(R.string.passwordUpperCase)
                        }
                    }
                    else
                    {
                        password.error = getString(R.string.passwordLowerCase)
                    }
                }
                else
                {
                    password.error = getString(R.string.passwordDigit)
                }

            }
            else
            {
                    mail.error = getString(R.string.invalidMail)
            }


        }
        sigup.setOnClickListener {
            val intent=Intent(this, SigupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}