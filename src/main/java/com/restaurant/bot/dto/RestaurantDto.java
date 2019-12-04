package com.restaurant.bot.dto;

import java.util.List;

public class RestaurantDto {

    private Integer restaurant_id;
    private String name;
    private String street;
    private String zone;
    private String latitude;
    private String longitude;
    private String images;
    private String date;
    private List<PersonDto> personList;
    private List<PlaceDto> placeList;

    public RestaurantDto() {
    }

    public RestaurantDto(String name) {
        this.name = name;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PersonDto> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonDto> personList) {
        this.personList = personList;
    }

    public List<PlaceDto> getPlaceList() {
        return placeList;
    }

    public void setPlaceList(List<PlaceDto> placeList) {
        this.placeList = placeList;
    }
}
