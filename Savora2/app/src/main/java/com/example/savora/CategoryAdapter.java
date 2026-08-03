package com.example.savora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;

    public interface OnCategoryClickListener{
        void onCategoryClick(String categoryName);
    }

    private OnCategoryClickListener listener;

    public CategoryAdapter(List<Category> categoryList,
                           OnCategoryClickListener listener){

        this.categoryList = categoryList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Category category = categoryList.get(position);

        holder.txtCategory.setText(category.getStrCategory());

        Glide.with(holder.itemView.getContext())
                .load(category.getStrCategoryThumb())
                .into(holder.imgCategory);


        holder.itemView.setOnClickListener(v -> {
            listener.onCategoryClick(category.getStrCategory());
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView txtCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtCategory = itemView.findViewById(R.id.txtCategory);
        }
    }
}