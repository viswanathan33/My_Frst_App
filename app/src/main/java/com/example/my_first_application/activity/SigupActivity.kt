package com.example.my_first_application.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.model.UserInfo
import com.google.gson.Gson

class SigupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val actionBar = supportActionBar
        val name=findViewById<EditText>(R.id.editTextTextPersonName)
        val email=findViewById<EditText>(R.id.editTextTextEmailAddress1)
        val password=findViewById<EditText>(R.id.editTextTextPassword1)
        val confirmPassword=findViewById<EditText>(R.id.editTextTextPassword_confirm)
        val sigup=findViewById<Button>(R.id.button_signup)
        val login=findViewById<TextView>(R.id.textView_login)
        val sharedPreferences=getSharedPreferences(Constants.share_pref, Context.MODE_PRIVATE)
        actionBar!!.hide()
        sigup.setOnClickListener {
            val gson=Gson()
            val userInfo= UserInfo()
            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            val json:String=gson.toJson(userInfo)
            userInfo.setName(name.text.toString())
            userInfo.setEmail(email.text.toString())
            userInfo.setpassword(password.text.toString())
            userInfo.setConfirmPassword(confirmPassword.text.toString())
            if (userInfo.getName()?.isEmpty()!!)
            {
                name.error = getString(R.string.nameEmpty)
            }
            else if (userInfo.getEmail()?.isEmpty()!!)
            {
                email.error = getString(R.string.mailEmpty)
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
                else if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternDigit.toRegex()))
                {
                    if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternLowerCase.toRegex()))
                    {
                        if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternUpperCase.toRegex()))
                        {
                            if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternSplChar.toRegex()))
                            {
                                if (userInfo.getPassword()!! == userInfo.getConfirmPassword())
                                {
                                    editor.putString(Constants.USER_INFO, json)
                                    editor.apply()
                                    Constants.callActivity(this, HomeActivity::class.java)
                                    finish()
                                }
                                else
                                {
                                    confirmPassword.error = getString(R.string.passwordMatch)
                                }
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
                email.error = getString(R.string.invalidMail)
            }

        }
        login.setOnClickListener {
            Constants.callActivity(this, LoginActivity::class.java)
            finish()
        }
    }
}
