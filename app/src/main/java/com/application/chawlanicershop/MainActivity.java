package com.application.chawlanicershop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG,"start of project");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchMenuActivity(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        Log.d(LOG_TAG,"use intent to call menuactivity");
        startActivity(intent);
    }
}
