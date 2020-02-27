package com.example.ecom.ui.mycart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecom.R;

public class MyCartFragment extends Fragment {

    private MyCartViewModel myCartViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myCartViewModel =
                ViewModelProviders.of(this).get(MyCartViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myaccount, container, false);

        return root;
    }
}
