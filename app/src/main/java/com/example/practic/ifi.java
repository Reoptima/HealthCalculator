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

public class ifi extends AppCompatActivity {
    private EditText diaPressure, sysPressure, pulse, age, mass, height;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifi);
        pulse = findViewById(R.id.editTextNumberDecimal0);
        sysPressure = findViewById(R.id.editTextNumberDecimal1);
        diaPressure = findViewById(R.id.editTextNumberDecimal2);
        age = findViewById(R.id.editTextNumberDecimal3);
        mass = findViewById(R.id.editTextNumberDecimal4);
        height = findViewById(R.id.editTextNumberDecimal5);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(ifi.this, MainActivity.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        btn.setOnClickListener(view -> {
            if (diaPressure.getText().length() == 0 || pulse.getText().length() == 0 || sysPressure.getText().length() == 0 || age.getText().length() == 0 || mass.getText().length() == 0 || height.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(diaPressure.getText().toString());
                Double.parseDouble(sysPressure.getText().toString());
                Double.parseDouble(age.getText().toString());
                Double.parseDouble(mass.getText().toString());
                Double.parseDouble(height.getText().toString());
                Double.parseDouble(pulse.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double dpr = Double.parseDouble(diaPressure.getText().toString());
                double spr = Double.parseDouble(sysPressure.getText().toString());
                double voz = Double.parseDouble(age.getText().toString());
                double ves = Double.parseDouble(mass.getText().toString());
                double ros = Double.parseDouble(height.getText().toString());
                double p = Double.parseDouble(pulse.getText().toString());
                double ifi = 0.011 * p + 0.014 * spr + 0.008 * dpr + 0.014 * voz + 0.009 * ves - 0.009 * ros - 0.27;
                resStr = String.format("%.2f", ifi);
                if (ifi < 2.6) {
                    result.setText(String.format("%s всё отлично", resStr));
                } else if (ifi >= 2.6 && ifi <= 3.9) {
                    result.setText(String.format("%s удовлетворительно", resStr));
                } else if (ifi > 3.9) {
                    result.setText(String.format("%s плохо", resStr));
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("IfiVal", resStr);
                editor.apply();
            }
        });
    }
}