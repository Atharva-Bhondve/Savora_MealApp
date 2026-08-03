package com.example.savora;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {

    Context context;
    List<Meal> mealList;

    public MealAdapter(Context context, List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Meal meal = mealList.get(position);

        holder.txtMealName.setText(meal.getStrMeal());
        holder.txtMealCategory.setText(meal.getStrCategory());
        holder.txtMealArea.setText(meal.getStrArea());

        Glide.with(context)
                .load(meal.getStrMealThumb())
                .into(holder.imgMeal);

        // Open Recipe Detail when clicked
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra("mealId", meal.getIdMeal());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgMeal;
        TextView txtMealName, txtMealCategory, txtMealArea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMeal = itemView.findViewById(R.id.imgMeal);
            txtMealName = itemView.findViewById(R.id.txtMealName);
            txtMealCategory = itemView.findViewById(R.id.txtMealCategory);
            txtMealArea = itemView.findViewById(R.id.txtMealArea);
        }
    }
}