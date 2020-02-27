package com.example.ecom.ui.myrewards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecom.R;

public class MyRewardsFragment extends Fragment {

    private MyRewardViewModel myRewardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myRewardViewModel =
                ViewModelProviders.of(this).get(MyRewardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rewards, container, false);

        return root;
    }
}
