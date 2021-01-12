package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.common.Constants;
import com.example.myfirstapp.model.UserInfo;
import com.google.gson.Gson;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    int count=0;
    TextView textView;
    EditText mail,password;
    Button btn;
    SharedPreferences sharedPreferences;
    String mailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[0-9])"+".{0,}$";
    String passwordPattern1="^(?=.*[a-z])"+".{0,}$";
    String passwordPattern2="^(?=.*[A-Z])"+".{0,}$";
    String passwordPattern3="^(?=.*[@#$%^&+=])"+".{0,}$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mail=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        textView=findViewById(R.id.textView6);
        btn=findViewById(R.id.log_button);
        sharedPreferences=getSharedPreferences(Constants.BundleKey.share_pref,MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                UserInfo userInfo = new UserInfo();
                userInfo.setEmail(mail.getText().toString());
                userInfo.setPassword(password.getText().toString());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String json=gson.toJson(userInfo);
                if (userInfo.getEmail().isEmpty())
                {
                    mail.setError(Constants.BundleKey.mail_condition1);
                }
                else
                    {
                    if (userInfo.getEmail().trim().matches(mailPattern))
                    {
                        if (userInfo.getPassword().isEmpty())
                        {
                            password.setError(Constants.BundleKey.password_condition1);
                        }
                        else
                            {
                                if(password.length()<8)
                                {
                                    password.setError(Constants.BundleKey.password_condition2);
                                }
                                else
                                {
                                    if (userInfo.getPassword().trim().matches(passwordPattern))
                                    {
                                        if (userInfo.getPassword().trim().matches(passwordPattern1))
                                        {
                                            if (userInfo.getPassword().trim().matches(passwordPattern2))
                                            {
                                                if (userInfo.getPassword().trim().matches(passwordPattern3))
                                                {
                                                    //editor.putString(Constants.BundleKey.sh_pre_mail,json);
                                                    editor.putString(Constants.BundleKey.USER_INFO,json);
                                                  /*editor.putString(Constants.BundleKey.sh_pre_mail,userInfo.getEmail());
                                                   editor.putString(Constants.BundleKey.sh_pre_password,userInfo.getPassword());*/
                                                    editor.apply();
                                                    //Log.d("TAG","data===>"+json);
                                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                                else
                                                {
                                                    password.setError(Constants.BundleKey.password_condition3);
                                                }
                                            }
                                            else
                                            {
                                                password.setError(Constants.BundleKey.password_condition4);
                                            }
                                        }
                                        else
                                        {
                                            password.setError(Constants.BundleKey.password_condition5);
                                        }
                                    }
                                    else
                                    {
                                        password.setError(Constants.BundleKey.password_condition6);
                                    }
                                }

                        }
                    }
                    else
                    {
                        mail.setError(Constants.BundleKey.mail_condition2);
                    }

                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                    startActivity(intent);
                    finish();
            }
        });
    }
}