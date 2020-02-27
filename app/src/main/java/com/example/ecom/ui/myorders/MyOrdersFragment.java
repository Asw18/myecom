package com.example.ecom.ui.myorders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecom.R;

public class MyOrdersFragment extends Fragment {

    private MyOrdersViewModel myOrdersViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myOrdersViewModel =
                ViewModelProviders.of(this).get(MyOrdersViewModel.class);
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        return root;
    }
}
