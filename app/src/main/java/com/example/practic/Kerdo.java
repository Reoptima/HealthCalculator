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

public class Kerdo extends AppCompatActivity {
    private EditText diaPressure, pulse;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kerdo);
        diaPressure = findViewById(R.id.editTextNumberDecimal0);
        pulse = findViewById(R.id.editTextNumberDecimal1);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(Kerdo.this, ifi.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        btn.setOnClickListener(view -> {
            if (diaPressure.getText().length() == 0 || pulse.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(diaPressure.getText().toString());
                Double.parseDouble(pulse.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double dpr = Double.parseDouble(diaPressure.getText().toString());
                double p = Double.parseDouble(pulse.getText().toString());
                double vi = 100 * (1 - (dpr / p));
                resStr = String.format("%.2f", vi);
                if (vi >= 31) {
                    result.setText(String.format("%s выраженная симпатикотония", resStr));
                } else if (vi >= 16 && vi <= 30) {
                    result.setText(String.format("%s симпатикотония", resStr));
                } else if (vi >= -15 && vi <= 15) {
                    result.setText(String.format("%s уравновешенность", resStr));
                } else if (vi >= -16 && vi <= -30) {
                    result.setText(String.format("%s парасимпатиотония", resStr));
                } else if (vi <= -30) {
                    result.setText(String.format("%s высокий показатель", resStr));
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("KerdoVal", resStr);
                editor.apply();
            }
        });
    }
}