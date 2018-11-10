package com.example.admin.du_an_1.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.du_an_1.UI.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed (new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent (SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish ();
            }
        }, 2000);
        //Start home activity
        //         startActivity(new Intent(this,MainActivity.class));
        //         //Close Splash
    }
}
