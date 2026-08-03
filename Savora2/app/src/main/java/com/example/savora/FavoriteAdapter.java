package com.example.savora;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.savora.database.FavoriteMeal;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    List<FavoriteMeal> favoriteList;

    public FavoriteAdapter(List<FavoriteMeal> favoriteList) {
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FavoriteMeal meal = favoriteList.get(position);

        holder.txtFavName.setText(meal.mealName);
        holder.txtFavCategory.setText(meal.category);
        holder.txtFavArea.setText(meal.area);

        Glide.with(holder.itemView.getContext())
                .load(meal.image)
                .into(holder.imgFavorite);
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFavorite;
        TextView txtFavName, txtFavCategory, txtFavArea;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFavorite = itemView.findViewById(R.id.imgFavorite);
            txtFavName = itemView.findViewById(R.id.txtFavName);
            txtFavCategory = itemView.findViewById(R.id.txtFavCategory);
            txtFavArea = itemView.findViewById(R.id.txtFavArea);
        }
    }
}