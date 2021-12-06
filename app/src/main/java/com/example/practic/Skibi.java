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

public class Skibi extends AppCompatActivity {
    private EditText l_Capacity, proba, pulse;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skibi);
        l_Capacity = findViewById(R.id.editTextNumberDecimal0);
        proba = findViewById(R.id.editTextNumberDecimal2);
        pulse = findViewById(R.id.editTextNumberDecimal3);
        result = findViewById(R.id.result_txt);
        btn = findViewById(R.id.calculate_btn);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(Skibi.this, Kerdo.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });

        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        btn.setOnClickListener(view -> {
            if (l_Capacity.getText().length() == 0 || proba.getText().length() == 0 || pulse.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(l_Capacity.getText().toString());
                Double.parseDouble(proba.getText().toString());
                Double.parseDouble(pulse.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double lC = Double.parseDouble(l_Capacity.getText().toString());
                Double ph = Double.parseDouble(proba.getText().toString());
                Double pul = Double.parseDouble(pulse.getText().toString());
                double kv = ((lC / 100) * ph) / pul;
                if (kv > 0) {
                    resStr = String.format("%.2f", kv);
                    if (kv < 5) {
                        result.setText(String.format("%s очень плохо", resStr));
                    } else if (kv >= 5 && kv <= 10) {
                        result.setText(String.format("%s неудовлетворительно", resStr));
                    } else if (kv >= 10 && kv <= 30) {
                        result.setText(String.format("%s удовлетворительно", resStr));
                    } else if (kv >= 30 && kv <= 60) {
                        result.setText(String.format("%s норма", resStr));
                    } else if (kv > 60) {
                        result.setText(String.format("%s очень хорошо", resStr));
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("SkibiVal", resStr);
                    editor.apply();
                } else {
                    Toast.makeText(getApplicationContext(), "Значение не может быть отрицательным", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}