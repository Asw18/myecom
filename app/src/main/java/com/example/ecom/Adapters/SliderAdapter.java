package com.example.ecom.Adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.ecom.R;
import com.example.ecom.model.SliderModel;

import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private List<SliderModel> sliderModelList;

    public SliderAdapter(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return sliderModelList.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.slider_layout,container,false);
        ImageView slideImage = view.findViewById(R.id.slide_banner);
        ConstraintLayout sliderContainer = view.findViewById(R.id.slider_container);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sliderContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderModelList.get(position).getBackGroundCoor())));
        }
        slideImage.setImageResource(sliderModelList.get(position).getSliderImageLink());
        container.addView(view,0);
        return  view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }
}
