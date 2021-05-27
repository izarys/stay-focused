package com.example.stayfocusedapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }

    public void switchActivityToLevel(View view) {
        Intent switchActivityIntent = new Intent(getApplicationContext(), LevelActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchActivityToTimer(View view) {
        Intent switchActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(switchActivityIntent);
    }

    public void switchActivityToStatistics(View view) {
        Intent switchActivityIntent = new Intent(getApplicationContext(), StatisticsActivity.class);
        startActivity(switchActivityIntent);
    }
}