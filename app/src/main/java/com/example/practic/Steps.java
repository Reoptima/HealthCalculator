package com.example.practic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Steps extends AppCompatActivity {
    private EditText stepsET;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isInteger = true;
    public boolean isStart;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        stepsET = findViewById(R.id.editTextNumberDecimal0);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(Steps.this, Stamina.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        btn.setOnClickListener(view -> {
            if (stepsET.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Забыли ввести данные", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isInteger = true;
                Integer.parseInt(stepsET.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isInteger = false;
            }
            if (isInteger) {
                int steps = Integer.parseInt(stepsET.getText().toString());
                if (steps > 0) {
                    resStr = String.valueOf(steps);
                    if (steps < 5000)
                        result.setText(String.format("%s сидячий образ жизни", steps));
                    else if (steps <= 9999)
                        result.setText(String.format("%s несколько активный образ жизни", steps));
                    else if (steps < 12000)
                        result.setText(String.format("%s активный образ жизни", steps));
                    else {
                        result.setText(String.format("%s очень активный образ жизни", steps));
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("StepsVal", resStr);
                    editor.apply();
                } else
                    Toast.makeText(getApplicationContext(), "Ты как отрицательно шагал...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}