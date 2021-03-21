package com.example.mahjonggamev1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.mahjonggamev1.LauncherActivity.SPLASH_SCREEN;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN);
    }


}
