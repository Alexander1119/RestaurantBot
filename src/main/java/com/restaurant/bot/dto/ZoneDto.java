package com.restaurant.bot.dto;

import com.restaurant.bot.domain.Zone;

import java.util.List;

public class ZoneDto  {

    private Integer zone_id;
    private String name_zone;

    private List<StreetDto> streetList;

    public ZoneDto(Zone zone) {
        this.name_zone = zone.getNameZone();
    }

    public Integer getZone_id() {
        return zone_id;
    }

    public void setZone_id(Integer zone_id) {
        this.zone_id = zone_id;
    }

    public String getName_zone() {
        return name_zone;
    }

    public void setName_zone(String name_zone) {
        this.name_zone = name_zone;
    }

    public List<StreetDto> getStreetList() {
        return streetList;
    }

    public void setStreetList(List<StreetDto> streetList) {
        this.streetList = streetList;
    }
}
