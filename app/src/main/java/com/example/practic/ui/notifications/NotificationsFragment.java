package com.example.practic.ui.notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.practic.R;

import java.util.concurrent.atomic.AtomicInteger;

public class NotificationsFragment extends Fragment {
    private TextView BMI, Steps, Stamina, Robinson, LiveIndex, Skibi, Kerdo, Ifi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        BMI = view.findViewById(R.id.BMI_Rez);
        Steps = view.findViewById(R.id.Steps_Rez);
        Stamina = view.findViewById(R.id.Stamina_Rez);
        Robinson = view.findViewById(R.id.Robinson_Rez);
        LiveIndex = view.findViewById(R.id.LiveIn_Rez);
        Skibi = view.findViewById(R.id.Skibi_Rez);
        Kerdo = view.findViewById(R.id.Kerdo_Rez);
        Ifi = view.findViewById(R.id.Ifi_Rez);
        SharedPreferences sp = getActivity().getApplicationContext().getSharedPreferences("TestResult", Context.MODE_PRIVATE);
        //Сброс сохранений
        ImageView reset = view.findViewById(R.id.gerbImg);
        AtomicInteger i = new AtomicInteger();
        AtomicInteger j = new AtomicInteger();
        j.set(5);
        reset.setOnClickListener(view1 -> { //Нажать 5 раз и будет сброс
            i.getAndIncrement();
            j.getAndDecrement();
            Toast.makeText(getActivity().getApplicationContext(), "Для сброса осталось нажать " + j, Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getActivity().getSharedPreferences("TestResult", Context.MODE_PRIVATE);
            if (i.get() == 5) {
                preferences.edit().remove("BMIVal").commit();
                preferences.edit().remove("StepsVal").commit();
                preferences.edit().remove("StaminaVal").commit();
                preferences.edit().remove("RobinsonVal").commit();
                preferences.edit().remove("LiveInVal").commit();
                preferences.edit().remove("SkibiVal").commit();
                preferences.edit().remove("KerdoVal").commit();
                preferences.edit().remove("IfiVal").commit();
                i.set(0);
                j.set(5);
            }
        });
        //Присваивание
        if (sp.getString("BMIVal", "") != "") {
            BMI.setText(String.format("Индекс массы тела: %s\nНорма 18,5-25", sp.getString("BMIVal", "")));
            Steps.setText(String.format("Шагов в день: %s\nНорма 10-12 тыс", sp.getString("StepsVal", "")));
            Stamina.setText(String.format("Коэффицент выносливости: %s\nНорма ниже 16", sp.getString("StaminaVal", "")));
            Robinson.setText(String.format("Индекс Робинсона: %s\nНорма 81-100", sp.getString("RobinsonVal", "")));
            LiveIndex.setText(String.format("Жизненный индекс: %s\nНорма выше 50", sp.getString("LiveInVal", "")));
            Skibi.setText(String.format("Коэффицент Скибинки: %s\nНорма 10-60", sp.getString("SkibiVal", "")));
            Kerdo.setText(String.format("Индекс Кердо: %s\nНорма от -15 до 15", sp.getString("KerdoVal", "")));
            Ifi.setText(String.format("Индекс функциональных изменений: %s\nНорма 2,6-3,09", sp.getString("IfiVal", "")));
        }
        return view;
    }
}