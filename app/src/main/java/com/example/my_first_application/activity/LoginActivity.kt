package com.example.my_first_application.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.my_first_application.R
import com.example.my_first_application.constant.Constants
import com.example.my_first_application.data.UserDao
import com.example.my_first_application.data.UserDetails
import com.example.my_first_application.data.UserLogIn
import com.example.my_first_application.data.UserViewModel
import com.example.my_first_application.model.UserInfo
import com.google.gson.Gson

class LoginActivity : AppCompatActivity() {
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mUserViewModel= ViewModelProvider(this).get(UserViewModel::class.java)
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
                else if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternDigit.toRegex()))
                {
                    if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternLowerCase.toRegex()))
                    {
                        if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternUpperCase.toRegex()))
                        {
                            if (userInfo.getPassword()!!.trim().matches(Constants.passwordPatternSplChar.toRegex()))
                            {
                                val mMail=mail.text.toString()
                                val mPassword=password.text.toString()
                                val userLogIn:UserLogIn=mUserViewModel.logInCheck(mMail,mPassword)
                                Log.d("TAG","data===>"+userLogIn)
                                if(userLogIn==null)
                                {
                                    Toast.makeText(applicationContext, "invalid mail or password", Toast.LENGTH_SHORT).show()
                                }
                                else
                                {
                                    Toast.makeText(applicationContext, "Login succesfully", Toast.LENGTH_SHORT).show()
                                    editor.putString(Constants.USER_INFO, json)
                                    editor.apply()
                                    Constants.callActivity(this, HomeActivity::class.java)
                                    finish()
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
                    mail.error = getString(R.string.invalidMail)
            }


        }
        sigup.setOnClickListener {
            Constants.callActivity(this, SigupActivity::class.java)
            finish()
        }

    }
}