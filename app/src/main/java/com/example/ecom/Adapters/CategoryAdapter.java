package com.example.ecom.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecom.R;
import com.example.ecom.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        String icon = categoryModelList.get(position).getCategoryIconUrl();
        String name = categoryModelList.get(position).getCategoryName();
        holder.setCategoryName(name);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageCategory;
        private TextView textCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCategory = itemView.findViewById(R.id.category_image);
            textCategory = itemView.findViewById(R.id.category_text);

        }

        private void  setCategoryIcon(){

        }

        private void  setCategoryName(String name){
            textCategory.setText(name);
        }
    }
}
