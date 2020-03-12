package com.example.recyclerviewcountries;

public class Model {
    private String countryName;
    private String countryArea;
    private int image;
    public String getCountryName() {
        return countryName;
    }
    public String getCountryArea() {
        return countryArea;
    }
    public int getImage() {
        return image;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    public void setCountryArea(String countryArea) {
        this.countryArea = countryArea;
    }
    public void setImage(int image) {
        this.image = image;
    }
}
