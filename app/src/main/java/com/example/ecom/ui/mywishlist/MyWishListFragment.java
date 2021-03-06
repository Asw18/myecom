package com.example.ecom.ui.mywishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecom.R;

public class MyWishListFragment extends Fragment {

    private MyWishListViewModel myWishListViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myWishListViewModel =
                ViewModelProviders.of(this).get(MyWishListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_wishlist, container, false);

        return root;
    }
}
