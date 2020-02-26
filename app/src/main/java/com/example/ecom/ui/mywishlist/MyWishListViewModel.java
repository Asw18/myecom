package com.example.ecom.ui.mywishlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyWishListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyWishListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is wishlist fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}