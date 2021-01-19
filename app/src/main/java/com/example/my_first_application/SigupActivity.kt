package com.example.my_first_application

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson

class SigupActivity : AppCompatActivity() {
    var mailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    var passwordPattern = "^(?=.*[0-9])" + ".{0,}$"
    var passwordPattern1 = "^(?=.*[a-z])" + ".{0,}$"
    var passwordPattern2 = "^(?=.*[A-Z])" + ".{0,}$"
    var passwordPattern3 = "^(?=.*[@#$%^&+=])" + ".{0,}$"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val name=findViewById<EditText>(R.id.editTextTextPersonName)
        val email=findViewById<EditText>(R.id.editTextTextEmailAddress1)
        val password=findViewById<EditText>(R.id.editTextTextPassword1)
        val confirm_paassword=findViewById<EditText>(R.id.editTextTextPassword_confirm)
        val sigup=findViewById<Button>(R.id.button_signup)
        val login=findViewById<TextView>(R.id.textView_login)
        val sharedPreferences=getSharedPreferences(Constants.BundleKey.share_pref, Context.MODE_PRIVATE)
        sigup.setOnClickListener {
            val gson=Gson()
            val userInfo=UserInfo()
            userInfo.setName(name.text.toString())
            userInfo.setEmail(email.text.toString())
            userInfo.setpassword(password.text.toString())
            userInfo.setConfirm_password(confirm_paassword.text.toString())
            val editor:SharedPreferences.Editor=sharedPreferences.edit()
            val json:String=gson.toJson(userInfo)
            if (userInfo.getName()?.isEmpty()!!){
                name.setError(Constants.BundleKey.name_condition)
            }
            else{
                if (userInfo.getEmail()?.isEmpty()!!) {
                    email.setError(Constants.mail_condition1)
                } else {
                    if (userInfo.getEmail()!!.trim().matches(mailPattern.toRegex())) {
                        if (userInfo.getPassword()?.isEmpty()!!) {
                            password.setError(Constants.password_condition1)
                        } else {
                            if (userInfo.getPassword()!!.length < 8) {
                                password.setError(Constants.password_condition2)
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
                                                if (userInfo.getPassword()!!.equals(userInfo.getConfirm_password())){
                                                    editor.putString(Constants.BundleKey.USER_INFO, json)
                                                    editor.apply()
                                                    val intent = Intent(this, HomeActivity::class.java)
                                                    startActivity(intent)
                                                    finish()
                                                }
                                                else{
                                                    confirm_paassword.setError(Constants.BundleKey.confirm_password_condition)
                                                }
                                            } else {
                                                password.setError(Constants.BundleKey.password_condition3)
                                            }
                                        } else {
                                            password.setError(Constants.BundleKey.password_condition4)
                                        }
                                    } else {
                                        password.setError(Constants.BundleKey.password_condition5)
                                    }
                                } else {
                                    password.setError(Constants.BundleKey.password_condition6)
                                }
                            }
                        }
                    } else {
                        email.setError(Constants.mail_condition2)
                    }

                }
            }
        }
        login.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}