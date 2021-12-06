package com.example.practic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Robinson extends AppCompatActivity {
    private EditText pulse, sysPressure;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robinson);
        pulse = findViewById(R.id.editTextNumberDecimal0);
        sysPressure = findViewById(R.id.editTextNumberDecimal2);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(Robinson.this, liveindex.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        btn.setOnClickListener(view -> {
            if (pulse.getText().length() == 0 || sysPressure.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(pulse.getText().toString());
                Double.parseDouble(sysPressure.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double p = Double.parseDouble(pulse.getText().toString());
                double spr = Double.parseDouble(sysPressure.getText().toString());
                double kp = (p * spr) / 100;
                if (kp > 0) {
                    resStr = String.format("%.2f", kp);
                    if (kp <= 74) {
                        result.setText(String.format("%s высокий уровень", resStr));
                    } else if (kp >= 75 && kp<=80) {
                        result.setText(String.format("%s выше среднего", resStr));
                    } else if (kp >= 81 && kp<=90) {
                        result.setText(String.format("%s норма", resStr));
                    } else if (kp >= 91 && kp <=100) {
                        result.setText(String.format("%s ниже среднего", resStr));
                    } else if (kp >= 101) {
                        result.setText(String.format("%s низкий уровень", resStr));
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("RobinsonVal", resStr);
                    editor.apply();
                } else {
                    Toast.makeText(getApplicationContext(), "Значение не может быть отрицательным", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}