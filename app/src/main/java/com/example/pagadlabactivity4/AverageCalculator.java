package com.example.pagadlabactivity4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AverageCalculator extends AppCompatActivity {

    TextView txtEnglish, txtMath, txtScience, txtMAPEH, txtFilipino;
    Button btnCompute, btnBackToMenu;
    Intent calculate;
    Double mathGrade, englishGrade, scienceGrade, mapehGrade, filipinoGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_average_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtEnglish = findViewById(R.id.txtEnglish);
        txtMath = findViewById(R.id.txtMath);
        txtScience = findViewById(R.id.txtScience);
        txtMAPEH = findViewById(R.id.txtMAPEH);
        txtFilipino = findViewById(R.id.txtFilipino);

        btnCompute = findViewById(R.id.btnCompute);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        calculate = new Intent(this, AverageResult.class);

        backToMenu();
        calculateAverage();
    }

    public void backToMenu(){
        btnBackToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void calculateAverage(){
        btnCompute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMath.getText().toString().isEmpty() || txtEnglish.getText().toString().isEmpty() || txtScience.getText().toString().isEmpty() || txtMAPEH.getText().toString().isEmpty() || txtFilipino.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill up all fields to continue.",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    mathGrade = Double.parseDouble(txtMath.getText().toString());
                    englishGrade = Double.parseDouble(txtEnglish.getText().toString());
                    scienceGrade = Double.parseDouble(txtScience.getText().toString());
                    filipinoGrade = Double.parseDouble(txtFilipino.getText().toString());
                    mapehGrade = Double.parseDouble(txtMAPEH.getText().toString());

                    if(mathGrade < 50 || mathGrade > 100 || englishGrade < 50 || englishGrade > 100 || scienceGrade < 50 || scienceGrade > 100 || filipinoGrade < 50 || filipinoGrade > 100 || mapehGrade < 50 || mapehGrade > 100){
                        Toast.makeText(getApplicationContext(),"Grades can only be in the range of 50 to 100.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        calculate.putExtra("math_grade", mathGrade);
                        calculate.putExtra("mapeh_grade", mapehGrade);
                        calculate.putExtra("english_grade", englishGrade);
                        calculate.putExtra("filipino_grade", filipinoGrade);
                        calculate.putExtra("science_grade", scienceGrade);

                        startActivity(calculate);
                    }
                }
            }
        });
    }
}