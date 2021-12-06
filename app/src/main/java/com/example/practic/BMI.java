package com.example.practic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMI extends AppCompatActivity {
    private EditText height;
    private EditText weight;
    TextView result;
    String resStr;
    Button btn, next;
    SharedPreferences sp;
    private TextView recommend;
    public boolean isDouble = true;
    public boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        height = findViewById(R.id.editTextNumberDecimal0);
        weight = findViewById(R.id.editTextNumberDecimal2);
        result = findViewById(R.id.result_txt);
        recommend = findViewById(R.id.recomend_txt);
        btn = findViewById(R.id.calculate_btn);
        next = findViewById(R.id.buttonNEXT);
        next.setOnClickListener(view -> {
            Intent intent2 = new Intent(BMI.this, Steps.class);
            startActivity(intent2);
            if (isStart == true) {
                finish();
            }
        });
        sp = getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        btn.setOnClickListener(view -> {
            if (height.getText().length() == 0 && weight.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Надо ввести рост и вес", Toast.LENGTH_SHORT).show();
                return;
            }
            if (height.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Забыли ввести рост", Toast.LENGTH_SHORT).show();
                return;
            }
            if (weight.getText().length() == 0) {
                Toast.makeText(getApplicationContext(), "Забыли ввести вес", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                isDouble = true;
                Double.parseDouble(height.getText().toString());
                Double.parseDouble(weight.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Что-то пошло не так, проверьте данные", Toast.LENGTH_SHORT).show();
                result.setText("Ошибка");
                isDouble = false;
            }
            if (isDouble) {
                double h = Double.parseDouble(height.getText().toString()) / 100.0;
                double w = Double.parseDouble(weight.getText().toString());
                double a = w / Math.pow(h, 2);
                double BMI = Math.round(a * 100.0) / 100.0;
                resStr = String.valueOf(BMI);
                if (BMI <= 10) {
                    result.setText(String.format("Ваш ИМТ %s\nВы Dead Inside", BMI));
                    recommend.setTextColor(Color.RED);
                    result.setTextColor(Color.RED);
                } else if (BMI <= 16.5) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас выраженный дефицит массы тела", BMI));
                    recommend.setTextColor(Color.RED);
                    result.setTextColor(Color.RED);
                } else if (BMI <= 18.4) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас дефицит массы тела", BMI));
                    recommend.setTextColor(Color.parseColor("#FFB300"));
                    result.setTextColor(Color.parseColor("#FFB300"));
                } else if (BMI <= 24.9) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас норма", BMI));
                    recommend.setTextColor(Color.parseColor("#43A047"));
                    result.setTextColor(Color.parseColor("#43A047"));
                } else if (BMI <= 30.0) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас избыток массы тела", BMI));
                    recommend.setTextColor(Color.parseColor("#FFB300"));
                    result.setTextColor(Color.parseColor("#FFB300"));
                } else if (BMI <= 34.9) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас ожирение первой степени", BMI));
                    recommend.setTextColor(Color.RED);
                    result.setTextColor(Color.RED);
                } else if (BMI <= 40.0) {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас ожирение второй степени", BMI));
                    recommend.setTextColor(Color.RED);
                    result.setTextColor(Color.RED);

                } else {
                    result.setText(String.format("Ваш ИМТ %s\nУ вас ожирение третьей степени", BMI));
                    recommend.setTextColor(Color.RED);
                    result.setTextColor(Color.RED);
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("BMIVal", resStr);
                editor.apply();
            }
        });
    }
}