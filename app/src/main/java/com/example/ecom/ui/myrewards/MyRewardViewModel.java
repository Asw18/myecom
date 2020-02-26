package com.example.ecom.ui.myrewards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyRewardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyRewardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rewards fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}