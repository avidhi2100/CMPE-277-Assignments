package com.example.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        Button finish = findViewById(R.id.buttonCF);
        finish.setOnClickListener(view -> { finish(); });
    }
}