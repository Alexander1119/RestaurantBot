/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "restaurant_timetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantTimetable.findAll", query = "SELECT r FROM RestaurantTimetable r"),
    @NamedQuery(name = "RestaurantTimetable.findByIdRestaurantTimetable", query = "SELECT r FROM RestaurantTimetable r WHERE r.idRestaurantTimetable = :idRestaurantTimetable")})
public class RestaurantTimetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_restaurant_timetable")
    private Integer idRestaurantTimetable;
    @JoinColumn(name = "foodlist_id", referencedColumnName = "foodlist_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Foodlist foodlistId;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;
    @JoinColumn(name = "timetable_id", referencedColumnName = "timetable_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Timetable timetableId;

    public RestaurantTimetable() {
    }

    public RestaurantTimetable(Integer idRestaurantTimetable) {
        this.idRestaurantTimetable = idRestaurantTimetable;
    }

    public Integer getIdRestaurantTimetable() {
        return idRestaurantTimetable;
    }

    public void setIdRestaurantTimetable(Integer idRestaurantTimetable) {
        this.idRestaurantTimetable = idRestaurantTimetable;
    }

    public Foodlist getFoodlistId() {
        return foodlistId;
    }

    public void setFoodlistId(Foodlist foodlistId) {
        this.foodlistId = foodlistId;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Timetable getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Timetable timetableId) {
        this.timetableId = timetableId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurantTimetable != null ? idRestaurantTimetable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantTimetable)) {
            return false;
        }
        RestaurantTimetable other = (RestaurantTimetable) object;
        if ((this.idRestaurantTimetable == null && other.idRestaurantTimetable != null) || (this.idRestaurantTimetable != null && !this.idRestaurantTimetable.equals(other.idRestaurantTimetable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.RestaurantTimetable[ idRestaurantTimetable=" + idRestaurantTimetable + " ]";
    }
    
}
