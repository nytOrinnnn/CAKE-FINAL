package com.example.cakev3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView startBtn = findViewById(R.id.startBtn);
        TextView historyBtn = findViewById(R.id.historyBtn);

        startBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FlavorPage.class);
            startActivity(intent);
        });

        historyBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });
    }
}
