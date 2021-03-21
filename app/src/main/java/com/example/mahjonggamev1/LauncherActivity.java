package com.example.mahjonggamev1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {

    public static final int SPLASH_SCREEN = 2000;

    Animation bottomAnimation, topAnimation;
    TextView statement;
    ImageView profile;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        statement = findViewById(R.id.statement);
        profile = findViewById(R.id.profile);

        statement.setAnimation(bottomAnimation);
        profile.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(LauncherActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        }, SPLASH_SCREEN);
    }



}
