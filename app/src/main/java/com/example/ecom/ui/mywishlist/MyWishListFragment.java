package com.example.ecom.ui.mywishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecom.R;

public class MyWishListFragment extends Fragment {

    private MyWishListViewModel myWishListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myWishListViewModel =
                ViewModelProviders.of(this).get(MyWishListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mycart, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        myWishListViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
