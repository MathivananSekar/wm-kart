package com.mathi.wmkart.domain;

public class Product {
    int uniqueId;
    String productName;
    String description;
    String category;
    String subCategory;
    double retailPrice;
    String imageUrl;
    String brand;
    float rating;
    String tags;
    int availableQuantity;

    private boolean selected;

    public Product() {
    }

    public Product(String productName, String description, double retailPrice, String imageUrl) {
        this.productName = productName;
        this.description = description;
        this.retailPrice = retailPrice;
        this.imageUrl = imageUrl;
    }

    public Product(String productName, String description, double retailPrice, String imageUrl, boolean selected) {
        this.productName = productName;
        this.description = description;
        this.retailPrice = retailPrice;
        this.imageUrl = imageUrl;
        this.selected = selected;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Product(int uniqueId, String productName, String description, String category, String subCategory, double retailPrice, String imageUrl, String brand, float rating, String tags, int availableQuantity) {
        this.uniqueId = uniqueId;
        this.productName = productName;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.retailPrice = retailPrice;
        this.imageUrl = imageUrl;
        this.brand = brand;
        this.rating = rating;
        this.tags = tags;
        this.availableQuantity = availableQuantity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uniqueId=" + uniqueId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", retailPrice=" + retailPrice +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand='" + brand + '\'' +
                ", rating=" + rating +
                ", tags='" + tags + '\'' +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
