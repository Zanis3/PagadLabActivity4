package com.example.pagadlabactivity4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton btnAverage, btnContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAverage = findViewById(R.id.btnAverage);
        btnContacts = findViewById(R.id.btnContacts);

        btnAverage.setOnClickListener(v -> {
            Intent intent = new Intent(this, AverageCalculator.class);
            startActivity(intent);
            finish();
        });

        btnContacts.setOnClickListener(v -> {
            Intent intent = new Intent(this, Contacts.class);
            startActivity(intent);
            finish();
        });
    }
}