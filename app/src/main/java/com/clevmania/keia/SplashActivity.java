package com.clevmania.keia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.clevmania.keia.ui.IntroManager;
import com.clevmania.keia.ui.OnBoardActivity;

public class SplashActivity extends AppCompatActivity {
    private IntroManager introManager;
    private static int splash_timer = 3000;
    private TextView logoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoView = findViewById(R.id.textView);
        Typeface logoTextFace = Typeface.createFromAsset(getAssets(),"Aventura-Bold.otf");
        logoView.setTypeface(logoTextFace);

    }

    @Override
    protected void onStart() {
        super.onStart();
        introManager = new IntroManager(this);
        if(introManager.IsFirstLaunch()){
            introManager.setAppsFirstLaunch(false);
            Intent intent = new Intent(this, OnBoardActivity.class);
            startActivity(intent);
            finish();
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },splash_timer);
        }
    }
}
