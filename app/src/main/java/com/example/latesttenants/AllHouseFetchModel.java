package com.example.latesttenants;

public class AllHouseFetchModel {
    String address, city, image, rent, state, title;

    public AllHouseFetchModel() {

    }

    public AllHouseFetchModel(String address, String city, String image, String rent, String state, String title) {
        this.address = address;
        this.city = city;
        this.image = image;
        this.rent = rent;
        this.state = state;
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
