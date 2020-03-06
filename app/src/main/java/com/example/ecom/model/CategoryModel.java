package com.example.ecom.model;

public class CategoryModel {
    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryModel(String categoryIconUrl, String categoryName) {
        this.categoryIconUrl = categoryIconUrl;
        this.categoryName = categoryName;
    }

    private String categoryIconUrl;
    private String categoryName;
}
