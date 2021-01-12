package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.common.Constants;
import com.example.myfirstapp.model.UserInfo;
import com.google.gson.Gson;

public class SignupActivity extends AppCompatActivity {
    TextView textView1;
    EditText name,eid,pwd,cpwd;
    Button sign;
    String mailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern="^(?=.*[0-9])"+".{0,}$";
    String passwordPattern1="^(?=.*[a-z])"+".{0,}$";
    String passwordPattern2="^(?=.*[A-Z])"+".{0,}$";
    String passwordPattern3="^(?=.*[@#$%^&+=])"+".{0,}$";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView1=findViewById(R.id.textView_login);
        name=findViewById(R.id.editTextTextPersonName);
        eid=findViewById(R.id.editTextTextEmailAddress1);
        pwd=findViewById(R.id.editTextTextPassword1);
        cpwd=findViewById(R.id.editTextTextPassword2);
        sign=findViewById(R.id.button_signup);
        sharedPreferences=getSharedPreferences(Constants.BundleKey.share_pref,MODE_PRIVATE);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson=new Gson();
                UserInfo userInfo=new UserInfo();
                userInfo.setName(name.getText().toString());
                userInfo.setEmail(eid.getText().toString());
                userInfo.setPassword(pwd.getText().toString());
                userInfo.setConfirm_password(cpwd.getText().toString());
                SharedPreferences.Editor editor=sharedPreferences.edit();
                String json=gson.toJson(userInfo);
                if (userInfo.getName().isEmpty())
                {
                    name.setError(Constants.BundleKey.name_condition);
                }
                else
                    {
                    if (userInfo.getEmail().isEmpty())
                    {
                        eid.setError(Constants.BundleKey.mail_condition1);
                    }
                    else
                        {
                        if (userInfo.getEmail().trim().matches(mailPattern)) {
                            if (userInfo.getPassword().isEmpty())
                            {
                                pwd.setError(Constants.BundleKey.password_condition1);
                            }
                            else
                                {
                                if (pwd.length() < 8) {
                                    pwd.setError(Constants.BundleKey.password_condition2);
                                }
                                else {
                                    if (userInfo.getPassword().trim().matches(passwordPattern)) {
                                        if (userInfo.getPassword().trim().matches(passwordPattern1)) {
                                            if (userInfo.getPassword().trim().matches(passwordPattern2)) {
                                                if (userInfo.getPassword().trim().matches(passwordPattern3)) {

                                                    if (userInfo.getPassword().equals(userInfo.getConfirm_password())) {
                                                        editor.putString(Constants.BundleKey.USER_INFO,json);
                                                        /*editor.putString(Constants.BundleKey.sh_pre_mail,userInfo.getEmail());
                                                        editor.putString(Constants.BundleKey.sh_pre_password,userInfo.getPassword());*/
                                                        editor.apply();
                                                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        cpwd.setError(Constants.BundleKey.confirm_password_condition);
                                                    }
                                                } else {
                                                    pwd.setError(Constants.BundleKey.password_condition3);
                                                }
                                            } else {
                                                pwd.setError(Constants.BundleKey.password_condition4);
                                            }
                                        } else {
                                            pwd.setError(Constants.BundleKey.password_condition5);
                                        }
                                    }
                                         else
                                                    {
                                                        pwd.setError(Constants.BundleKey.password_condition6);
                                                    }

                                                }

                                            }

                                        } else {
                                            eid.setError(Constants.BundleKey.mail_condition2);
                                        }
                                    }
                                }
                            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}