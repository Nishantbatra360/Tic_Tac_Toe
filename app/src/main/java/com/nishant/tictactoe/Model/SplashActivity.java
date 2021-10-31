package com.nishant.tictactoe.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;

import com.nishant.tictactoe.R;

public class SplashActivity extends AppCompatActivity {
    private RelativeLayout splashParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashParent=findViewById(R.id.splashParent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashParent.setBackgroundColor(Color.parseColor("#f4c2c2"));
            }
        },1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}