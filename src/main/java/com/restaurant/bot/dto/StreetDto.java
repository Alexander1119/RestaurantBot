package com.restaurant.bot.dto;

import com.restaurant.bot.domain.Street;

public class StreetDto {
    private Integer street_id;
    private String name_street;

    public StreetDto() {
    }

    public StreetDto(Street street) {
        this.name_street = street.getNameStreet();
    }

    public Integer getStreet_id() {
        return street_id;
    }

    public void setStreet_id(Integer street_id) {
        this.street_id = street_id;
    }

    public String getName_street() {
        return name_street;
    }

    public void setName_street(String name_street) {
        this.name_street = name_street;
    }
}
