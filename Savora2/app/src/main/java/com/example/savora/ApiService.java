package com.example.savora;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("search.php?s=")
    Call<MealResponse> getMeals();

    @GET("search.php")
    Call<MealResponse> searchMeals(@Query("s") String mealName);

    @GET("lookup.php")
    Call<MealResponse> getMealDetails(@Query("i") String mealId);

    @GET("filter.php")
    Call<MealResponse> getMealsByCategory(@Query("c") String category);
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("search.php")
    Call<MealResponse> searchByFirstLetter(
            @Query("f") String letter);

    @GET("list.php")
    Call<AreaResponse> getAreas(
            @Query("a") String list);

    @GET("filter.php")
    Call<MealResponse> getMealsByArea(
            @Query("a") String area);

    @GET("list.php")
    Call<IngredientResponse> getIngredients(
            @Query("i") String list);

    @GET("filter.php")
    Call<MealResponse> getMealsByIngredient(
            @Query("i") String ingredient);
}