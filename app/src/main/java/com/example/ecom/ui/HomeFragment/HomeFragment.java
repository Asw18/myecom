package com.example.ecom.ui.HomeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.ecom.Adapters.CategoryAdapter;
import com.example.ecom.Adapters.SliderAdapter;
import com.example.ecom.R;
import com.example.ecom.model.CategoryModel;
import com.example.ecom.model.SliderModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ViewPager slideViewPager;
    private List<SliderModel> sliderModelList;
    private  List<CategoryModel> categoryModelList;


   public HomeFragment(){

   }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_recyclerview);
        categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","home"));
        categoryModelList.add(new CategoryModel("link","home2"));
        categoryModelList.add(new CategoryModel("link","home3"));
        categoryModelList.add(new CategoryModel("link","home4"));
        categoryModelList.add(new CategoryModel("link","home5"));
        categoryModelList.add(new CategoryModel("link","home6"));
        categoryAdapter = new CategoryAdapter(categoryModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();








        slideViewPager = view.findViewById(R.id.bannerViewPager);
        sliderModelList = new ArrayList<SliderModel>();
        sliderModelList.add(new SliderModel(R.mipmap.icon_person,"#077Ae4"));
        sliderModelList.add(new SliderModel(R.drawable.icon_email_green,"#077Ae4"));
        sliderModelList.add(new SliderModel(R.drawable.icon_email_red,"#077Ae4"));
        sliderModelList.add(new SliderModel(R.drawable.icon_email_green,"#077Ae4"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        slideViewPager.setClipToPadding(false);
        slideViewPager.setPageMargin(20);
        slideViewPager.setAdapter(sliderAdapter);

        return view;
    }
}
