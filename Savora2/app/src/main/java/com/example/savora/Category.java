package com.example.savora;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("idCategory")
    private String idCategory;

    @SerializedName("strCategory")
    private String strCategory;

    @SerializedName("strCategoryThumb")
    private String strCategoryThumb;

    public String getIdCategory() {
        return idCategory;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public String getStrCategoryThumb() {
        return strCategoryThumb;
    }
}