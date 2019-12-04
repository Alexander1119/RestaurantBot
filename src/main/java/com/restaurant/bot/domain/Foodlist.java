/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "foodlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foodlist.findAll", query = "SELECT f FROM Foodlist f"),
    @NamedQuery(name = "Foodlist.findByFoodlistId", query = "SELECT f FROM Foodlist f WHERE f.foodlistId = :foodlistId"),
    @NamedQuery(name = "Foodlist.findByStatus", query = "SELECT f FROM Foodlist f WHERE f.status = :status")})
public class Foodlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "foodlist_id")
    private Integer foodlistId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodlistId", fetch = FetchType.LAZY)
    private Collection<RestaurantTimetable> restaurantTimetableCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodlistId", fetch = FetchType.LAZY)
    private Collection<FoodFoodlist> foodFoodlistCollection;

    public Foodlist() {
    }

    public Foodlist(Integer foodlistId) {
        this.foodlistId = foodlistId;
    }

    public Foodlist(Integer foodlistId, int status) {
        this.foodlistId = foodlistId;
        this.status = status;
    }

    public Integer getFoodlistId() {
        return foodlistId;
    }

    public void setFoodlistId(Integer foodlistId) {
        this.foodlistId = foodlistId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<RestaurantTimetable> getRestaurantTimetableCollection() {
        return restaurantTimetableCollection;
    }

    public void setRestaurantTimetableCollection(Collection<RestaurantTimetable> restaurantTimetableCollection) {
        this.restaurantTimetableCollection = restaurantTimetableCollection;
    }

    @XmlTransient
    public Collection<FoodFoodlist> getFoodFoodlistCollection() {
        return foodFoodlistCollection;
    }

    public void setFoodFoodlistCollection(Collection<FoodFoodlist> foodFoodlistCollection) {
        this.foodFoodlistCollection = foodFoodlistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodlistId != null ? foodlistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foodlist)) {
            return false;
        }
        Foodlist other = (Foodlist) object;
        if ((this.foodlistId == null && other.foodlistId != null) || (this.foodlistId != null && !this.foodlistId.equals(other.foodlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Foodlist[ foodlistId=" + foodlistId + " ]";
    }
    
}
