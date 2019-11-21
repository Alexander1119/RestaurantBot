package com.restaurant.bot.dto;

import java.util.List;

public class RestaurantDto {

    private Integer restaurant_id;
    private String name;
    private List<PersonDto> personList;
    private List<PlaceDto> placeList;

    public RestaurantDto(String name) {
        this.name = name;
    }

    public Integer getRestaurant_id() {
        return restaurant_id;
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
