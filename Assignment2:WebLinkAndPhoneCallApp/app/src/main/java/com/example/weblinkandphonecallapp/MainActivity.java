package com.example.weblinkandphonecallapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText url = findViewById(R.id.url);
        EditText phone = findViewById(R.id.phone);
        Button launch = findViewById(R.id.launch);
        Button ring = findViewById(R.id.ring);
        Button closeApp = findViewById(R.id.closeApp);


        launch.setOnClickListener(view -> {
            Uri webpage = Uri.parse(url.getText().toString());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        });

        ring.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phone.getText().toString()));
            startActivity(intent);
        });

        closeApp.setOnClickListener(view -> {
            finishAffinity();
        });

    }
}