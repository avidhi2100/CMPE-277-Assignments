package com.example.activitylifecycle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {

    int threadCount = 0;
    TextView thread_count;

    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread_count = findViewById(R.id.count);

        Button activityB = findViewById(R.id.buttonB);
        Button activityC = findViewById(R.id.buttonC);
        Button dialog = findViewById(R.id.dialog);
        Button close = findViewById(R.id.closeApp);

        thread_count.setText(String.valueOf(threadCount));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Simple Dialog")
                .setNegativeButton("CANCEL", (dialogInterface, i) -> dialogInterface.dismiss());

        activityB.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityB.class);
            threadCount+=5;
            startActivity(intent);
        }

        );

        activityC.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityC.class);
            threadCount += 10;
            startActivity(intent);
        }

        );
        dialog.setOnClickListener(view -> builder.create().show());
        close.setOnClickListener(view -> finish());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        thread_count.setText(String.valueOf(threadCount));
    }

}