package com.example.savora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvCategories, rvMeals;
    SearchView searchView;
    BottomNavigationView bottomNavigation;
    Button btnRandomMeal;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategories = findViewById(R.id.rvCategories);
        rvMeals = findViewById(R.id.rvMeals);
        searchView = findViewById(R.id.searchView);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        btnRandomMeal = findViewById(R.id.btnRandomMeal);

        rvCategories.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false));

        rvMeals.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getRetrofit().create(ApiService.class);

        loadCategories();
        loadMeals();

        // Search
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchMeals(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Random Meal
        btnRandomMeal.setOnClickListener(v -> {

            apiService.getRandomMeal().enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call,
                                       Response<MealResponse> response) {

                    if (response.isSuccessful()
                            && response.body() != null
                            && response.body().getMeals() != null) {

                        Meal meal = response.body().getMeals().get(0);

                        Intent intent =
                                new Intent(MainActivity.this,
                                        RecipeDetailActivity.class);

                        intent.putExtra("mealId", meal.getIdMeal());

                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call,
                                      Throwable t) {

                    Toast.makeText(MainActivity.this,
                            t.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });

        });

        // Bottom Navigation
        bottomNavigation.setSelectedItemId(R.id.nav_home);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                return true;

            } else if (item.getItemId() == R.id.nav_search) {

                startActivity(new Intent(MainActivity.this,
                        SearchActivity.class));

                return true;

            } else if (item.getItemId() == R.id.nav_favorite) {

                startActivity(new Intent(MainActivity.this,
                        FavoriteActivity.class));

                return true;

            } else if (item.getItemId() == R.id.nav_profile) {

                startActivity(new Intent(MainActivity.this,
                        ProfileActivity.class));

                return true;
            }

            return false;
        });

    }

    // Load Categories
    private void loadCategories() {

        apiService.getCategories().enqueue(new Callback<CategoryResponse>() {

            @Override
            public void onResponse(Call<CategoryResponse> call,
                                   Response<CategoryResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    CategoryAdapter adapter =
                            new CategoryAdapter(
                                    response.body().getCategories(),
                                    categoryName ->
                                            loadMealsByCategory(categoryName));

                    rvCategories.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call,
                                  Throwable t) {

                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Load Popular Meals
    private void loadMeals() {

        apiService.getMeals().enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call,
                                   Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    MealAdapter adapter =
                            new MealAdapter(MainActivity.this,
                                    response.body().getMeals());

                    rvMeals.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call,
                                  Throwable t) {

                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    // Search Meals
    private void searchMeals(String mealName) {

        apiService.searchMeals(mealName).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call,
                                   Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    MealAdapter adapter =
                            new MealAdapter(MainActivity.this,
                                    response.body().getMeals());

                    rvMeals.setAdapter(adapter);

                } else {

                    Toast.makeText(MainActivity.this,
                            "No meals found",
                            Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call,
                                  Throwable t) {

                Toast.makeText(MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    // Filter by Category
    private void loadMealsByCategory(String category) {

        apiService.getMealsByCategory(category)
                .enqueue(new Callback<MealResponse>() {

                    @Override
                    public void onResponse(Call<MealResponse> call,
                                           Response<MealResponse> response) {

                        if (response.isSuccessful()
                                && response.body() != null
                                && response.body().getMeals() != null) {

                            MealAdapter adapter =
                                    new MealAdapter(MainActivity.this,
                                            response.body().getMeals());

                            rvMeals.setAdapter(adapter);

                        }

                    }

                    @Override
                    public void onFailure(Call<MealResponse> call,
                                          Throwable t) {

                        Toast.makeText(MainActivity.this,
                                t.getMessage(),
                                Toast.LENGTH_SHORT).show();

                    }
                });

    }

}