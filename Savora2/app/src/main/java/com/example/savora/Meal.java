package com.example.savora;

import com.google.gson.annotations.SerializedName;

public class Meal {

    @SerializedName("idMeal")
    private String idMeal;

    @SerializedName("strMeal")
    private String strMeal;

    @SerializedName("strCategory")
    private String strCategory;

    @SerializedName("strArea")
    private String strArea;

    @SerializedName("strMealThumb")
    private String strMealThumb;

    public String getIdMeal() {
        return idMeal;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrArea() {
        return strArea;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }
    @SerializedName("strInstructions")
    private String strInstructions;

    @SerializedName("strYoutube")
    private String strYoutube;

    public String getStrInstructions() {
        return strInstructions;
    }

    public String getStrYoutube() {
        return strYoutube;
    }
}