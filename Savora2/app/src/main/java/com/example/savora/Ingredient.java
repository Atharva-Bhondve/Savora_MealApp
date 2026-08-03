package com.example.savora;

import com.google.gson.annotations.SerializedName;

public class Ingredient {

    @SerializedName("strIngredient")
    private String strIngredient;

    public String getStrIngredient() {
        return strIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }
}