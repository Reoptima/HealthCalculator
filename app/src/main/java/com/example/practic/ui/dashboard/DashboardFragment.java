package com.example.practic.ui.dashboard;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.practic.BMI;
import com.example.practic.Kerdo;
import com.example.practic.R;
import com.example.practic.Robinson;
import com.example.practic.Skibi;
import com.example.practic.Stamina;
import com.example.practic.Steps;
import com.example.practic.liveindex;
import com.example.practic.ifi;

public class DashboardFragment extends DialogFragment {
    Button btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        String[] gender = {"Мужчина", "Женщина"};
//        Spinner spinner = view.findViewById(R.id.spinner);

        btn = view.findViewById(R.id.bmi_btn);
        Stamina stamina = new Stamina();
        BMI bmi = new BMI();
        //СТАРТ
        LinearLayout Start_l = view.findViewById(R.id.start_Card);
        Start_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), BMI.class);
            startActivity(intent2);
            bmi.isStart = false;
            //stamina.Visible_bnt(0); todo невидимка
        });
        //Индекс массы тела
        LinearLayout BMI_l = view.findViewById(R.id.BMI_Card);
        BMI_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), BMI.class);
            startActivity(intent2);
        });
        //Шагомер
        LinearLayout Steps_l = view.findViewById(R.id.steps_Card);
        Steps_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), Steps.class);
            startActivity(intent2);
        });
        //Выносливость
        LinearLayout Stamina_l = view.findViewById(R.id.stamina_Card);
        Stamina_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), Stamina.class);
            startActivity(intent2);
        });
        //Робинсон
        LinearLayout Robinson_l = view.findViewById(R.id.robinson_Card);
        Robinson_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), Robinson.class);
            startActivity(intent2);
        });
        //Жизненный индекс
        LinearLayout LiveIn_l = view.findViewById(R.id.liveIn_Card);
        LiveIn_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), liveindex.class);
            startActivity(intent2);
        });
        //Скибински
        LinearLayout Skibi_l = view.findViewById(R.id.skibi_Card);
        Skibi_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), Skibi.class);
            startActivity(intent2);
        });
        //Кердо
        LinearLayout Kerdo_l = view.findViewById(R.id.kero_Card);
        Kerdo_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), Kerdo.class);
            startActivity(intent2);
        });
        LinearLayout Ifi_l = view.findViewById(R.id.ifi_Card);
        Ifi_l.setOnClickListener(view1 -> {
            Intent intent2 = new Intent(getActivity(), ifi.class);
            startActivity(intent2);
        });
        //Индекс функциональных изменений
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, gender);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
        return view;
    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//    }
}