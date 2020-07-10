package com.joe.owo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class Splash extends AppCompatActivity {

    MaterialButton campeign, donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        campeign = findViewById(R.id.signin_btn);
        donate = findViewById(R.id.signin_btn2);
        //Glide.with(getApplicationContext()).load(getResources().getDrawable(R.drawable.mutual))
        campeign.setOnClickListener(v -> {
            Intent intent = new Intent(Splash.this, Signin.class);
            startActivity(intent);
            finish();
        });

        donate.setOnClickListener(v -> {
            Intent intent = new Intent(Splash.this, Signin.class);
            startActivity(intent);
        });
    }
}
