package com.clevmania.keia.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.clevmania.keia.R;

public class RegisterActivity extends AppCompatActivity {
    private TextView headerText, or;
    private Button google, facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerText = findViewById(R.id.tv_header);
        or = findViewById(R.id.tv_or);
        facebook = findViewById(R.id.btn_facebook);
        google = findViewById(R.id.btn_google);

        String customFont = "Nunito-SemiBold.ttf";
        Typeface header = Typeface.createFromAsset(getAssets(),customFont);
        headerText.setTypeface(header);
        or.setTypeface(header);

    }

}
