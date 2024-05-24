package com.example.pagadlabactivity4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AverageResult extends AppCompatActivity {

    Button btnBackToCalculate;
    TextView lblAverageResult, lblPassOrFail;
    Intent calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_average_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        backToCalculate();
        finalAverage();
    }

    public void init(){
        btnBackToCalculate = findViewById(R.id.btnBackToCalculate);
        lblAverageResult = findViewById(R.id.lblAverageResult);
        lblPassOrFail = findViewById(R.id.lblPassOrFail);

        calculate = getIntent();
    }

    public void backToCalculate(){
        btnBackToCalculate.setOnClickListener(v -> {
            Intent intent = new Intent(this, AverageCalculator.class);
            startActivity(intent);
            finish();
        });
    }

    public void finalAverage(){
        Double english = calculate.getDoubleExtra("english_grade", 0);
        Double science = calculate.getDoubleExtra("science_grade", 0);
        Double math = calculate.getDoubleExtra("math_grade", 0);
        Double filipino = calculate.getDoubleExtra("filipino_grade", 0);
        Double mapeh = calculate.getDoubleExtra("mapeh_grade", 0);

        Double average = (english + science + math + filipino + mapeh) / 5;

        String averageString = String.format("%.2f", average);

        lblAverageResult.setText(averageString);

        if(average < 75){
            lblPassOrFail.setTextColor(Color.RED);
            lblPassOrFail.setText("FAILED");
        }
        else {
            lblPassOrFail.setTextColor(Color.GREEN);
            lblPassOrFail.setText("PASSED");
        }
    }
}