package com.example.practic.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Онлайн-калькулятор функциональльного состояния и массы тела");
    }

    public LiveData<String> getText() {
        return mText;
    }
}