package com.restaurant.bot.dto;

import com.restaurant.bot.domain.Place;
import com.restaurant.bot.domain.Zone;

import java.util.List;

public class PlaceDto {

    private Integer place_id;
    private String name_place;
    private List<ZoneDto> zoneList;

    public PlaceDto(Place place) {
        this.name_place = place.getNamePlace();
    }

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public String getName_place() {
        return name_place;
    }

    public void setName_place(String name_place) {
        this.name_place = name_place;
    }

    public List<ZoneDto> getZoneList() {
        return zoneList;
    }

    public void setZoneList(List<ZoneDto> zoneList) {
        this.zoneList = zoneList;
    }
}
