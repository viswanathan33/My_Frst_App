package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myfirstapp.common.Constants;

import java.util.Timer;
import java.util.TimerTask;

public class GreetingsActivity extends AppCompatActivity {
   Button btn;
   Timer timer;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(Constants.BundleKey.share_pref, MODE_PRIVATE);
        String sh_rf1 = preferences.getString(Constants.BundleKey.USER_INFO, "");
        //String sh_rf2 = preferences.getString(Constants.BundleKey.sh_pre_password, "");
        if (sh_rf1 == "") {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(GreetingsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
            btn = findViewById(R.id.button1);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.cancel();
                    Intent intent = new Intent(GreetingsActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
       else {
            Intent intent = new Intent(GreetingsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}