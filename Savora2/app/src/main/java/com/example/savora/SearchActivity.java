package com.example.savora;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    EditText etSearchName, etFirstLetter;
    Spinner spIngredient, spArea;
    RecyclerView rvSearchMeals;
    BottomNavigationView bottomNavigation;

    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearchName = findViewById(R.id.etSearchName);
        etFirstLetter = findViewById(R.id.etFirstLetter);
        spIngredient = findViewById(R.id.spIngredient);
        spArea = findViewById(R.id.spArea);
        rvSearchMeals = findViewById(R.id.rvSearchMeals);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        rvSearchMeals.setLayoutManager(new LinearLayoutManager(this));

        apiService = ApiClient.getRetrofit().create(ApiService.class);

        loadIngredients();
        loadAreas();

        // Search by Name
        etSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty()) {
                    searchByName(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Search by First Letter
        etFirstLetter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 1) {
                    searchByLetter(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Ingredient Spinner
        spIngredient.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent,
                                       android.view.View view,
                                       int position,
                                       long id) {

                if (position != 0) {
                    searchByIngredient(parent.getItemAtPosition(position).toString());
                }

            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

            }
        });

        // Area Spinner
        spArea.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent,
                                       android.view.View view,
                                       int position,
                                       long id) {

                if (position != 0) {
                    searchByArea(parent.getItemAtPosition(position).toString());
                }

            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {

            }
        });

        bottomNavigation.setSelectedItemId(R.id.nav_search);

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_home) {

                startActivity(new Intent(this, MainActivity.class));
                finish();
                return true;

            } else if (item.getItemId() == R.id.nav_search) {

                return true;

            } else if (item.getItemId() == R.id.nav_favorite) {

                startActivity(new Intent(this, FavoriteActivity.class));
                finish();
                return true;

            } else if (item.getItemId() == R.id.nav_profile) {

                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }

            return false;
        });

    }

    private void searchByName(String mealName) {

        apiService.searchMeals(mealName).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    rvSearchMeals.setAdapter(
                            new MealAdapter(SearchActivity.this,
                                    response.body().getMeals()));

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                Toast.makeText(SearchActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void searchByLetter(String letter) {

        apiService.searchByFirstLetter(letter).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    rvSearchMeals.setAdapter(
                            new MealAdapter(SearchActivity.this,
                                    response.body().getMeals()));

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                Toast.makeText(SearchActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void searchByIngredient(String ingredient) {

        apiService.getMealsByIngredient(ingredient).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    rvSearchMeals.setAdapter(
                            new MealAdapter(SearchActivity.this,
                                    response.body().getMeals()));

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                Toast.makeText(SearchActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void searchByArea(String area) {

        apiService.getMealsByArea(area).enqueue(new Callback<MealResponse>() {

            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().getMeals() != null) {

                    rvSearchMeals.setAdapter(
                            new MealAdapter(SearchActivity.this,
                                    response.body().getMeals()));

                }

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {

                Toast.makeText(SearchActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadIngredients() {

        apiService.getIngredients("list").enqueue(new Callback<IngredientResponse>() {

            @Override
            public void onResponse(Call<IngredientResponse> call,
                                   Response<IngredientResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<String> list = new ArrayList<>();
                    list.add("Select Ingredient");

                    for (Ingredient ingredient : response.body().getMeals()) {
                        list.add(ingredient.getStrIngredient());
                    }

                    spIngredient.setAdapter(new ArrayAdapter<>(
                            SearchActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            list));
                }

            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {

            }
        });

    }

    private void loadAreas() {

        apiService.getAreas("list").enqueue(new Callback<AreaResponse>() {

            @Override
            public void onResponse(Call<AreaResponse> call,
                                   Response<AreaResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<String> list = new ArrayList<>();
                    list.add("Select Area");

                    for (Area area : response.body().getMeals()) {
                        list.add(area.getStrArea());
                    }

                    spArea.setAdapter(new ArrayAdapter<>(
                            SearchActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            list));
                }

            }

            @Override
            public void onFailure(Call<AreaResponse> call, Throwable t) {

            }
        });

    }
}