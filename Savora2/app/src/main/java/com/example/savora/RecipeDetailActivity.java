package com.example.savora;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.savora.database.AppDatabase;
import com.example.savora.database.FavoriteMeal;
public class RecipeDetailActivity extends AppCompatActivity {

    ImageView imgMeal;
    TextView txtMealName, txtCategory, txtArea, txtInstructions;
    Button btnYoutube;

    ApiService apiService;

    String youtubeUrl = "";
    Button btnFavorite;

    SharedPreferences sharedPreferences;
    AppDatabase database;
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        database = AppDatabase.getInstance(this);

        imgMeal = findViewById(R.id.imgMeal);
        txtMealName = findViewById(R.id.txtMealName);
        txtCategory = findViewById(R.id.txtCategory);
        txtArea = findViewById(R.id.txtArea);
        txtInstructions = findViewById(R.id.txtInstructions);
        btnYoutube = findViewById(R.id.btnYoutube);

        btnFavorite = findViewById(R.id.btnFavorite);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

        String mealId = getIntent().getStringExtra("mealId");

        apiService = ApiClient.getRetrofit().create(ApiService.class);

        loadMealDetails(mealId);

        btnYoutube.setOnClickListener(v -> {

            if (!youtubeUrl.isEmpty()) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(youtubeUrl));
                startActivity(intent);
            }
        });
        btnFavorite.setOnClickListener(v -> {

            if (meal == null) {
                Toast.makeText(this,
                        "Meal not loaded yet",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            FavoriteMeal favoriteMeal = new FavoriteMeal();

            favoriteMeal.idMeal = meal.getIdMeal();
            favoriteMeal.mealName = meal.getStrMeal();
            favoriteMeal.category = meal.getStrCategory();
            favoriteMeal.area = meal.getStrArea();
            favoriteMeal.image = meal.getStrMealThumb();

            database.favoriteDao().insert(favoriteMeal);

            Toast.makeText(this,
                    "Added to Favorites",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void loadMealDetails(String mealId) {

        apiService.getMealDetails(mealId).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                     meal = response.body().getMeals().get(0);

                    txtMealName.setText(meal.getStrMeal());
                    txtCategory.setText("Category : " + meal.getStrCategory());
                    txtArea.setText("Area : " + meal.getStrArea());
                    txtInstructions.setText(meal.getStrInstructions());

                    youtubeUrl = meal.getStrYoutube();

                    Glide.with(RecipeDetailActivity.this)
                            .load(meal.getStrMealThumb())
                            .into(imgMeal);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

            }
        });
    }
}