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

public class Stamina extends AppCompatActivity {
    private EditText pulse, sysPressure, diaPressure;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamina);
        pulse = findViewById(R.id.editTextNumberDecimal0);
        sysPressure = findViewById(R.id.editTextNumberDecimal2);
        diaPressure = findViewById(R.id.editTextNumberDecimal3);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(Stamina.this, Robinson.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        btn.setOnClickListener(view -> {
            if (pulse.getText().length() == 0 || sysPressure.getText().length() == 0 || diaPressure.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(pulse.getText().toString());
                Double.parseDouble(sysPressure.getText().toString());
                Double.parseDouble(diaPressure.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double p = Double.parseDouble(pulse.getText().toString());
                Double spr = Double.parseDouble(sysPressure.getText().toString());
                Double dpr = Double.parseDouble(diaPressure.getText().toString());
                double kv = (p * 10) / (spr - dpr);
                if (kv > 0) {
                    resStr = String.format("%.2f", kv);
                    if (kv <= 16) {
                        result.setText(String.format("%s усиление кардиореспираторной системы", resStr));
                    } else if (kv > 16) {
                        result.setText(String.format("%s ослабление сердечно-сосудистой системы", resStr));
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("StaminaVal", resStr);
                    editor.apply();
                } else {
                    Toast.makeText(getApplicationContext(), "Значение не может быть отрицательным", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}