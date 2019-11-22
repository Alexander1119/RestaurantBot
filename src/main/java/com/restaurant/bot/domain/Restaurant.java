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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r"),
    @NamedQuery(name = "Restaurant.findByRestaurantId", query = "SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId"),
    @NamedQuery(name = "Restaurant.findByName", query = "SELECT r FROM Restaurant r WHERE r.name = :name")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "name")
    private int name;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person personId;
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Place placeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Collection<RestaurantFoodType> restaurantFoodTypeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Collection<RestaurantTimetable> restaurantTimetableCollection;

    public Restaurant() {
    }

    public Restaurant(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant(Integer restaurantId, int name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    public Place getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Place placeId) {
        this.placeId = placeId;
    }

    @XmlTransient
    public Collection<RestaurantFoodType> getRestaurantFoodTypeCollection() {
        return restaurantFoodTypeCollection;
    }

    public void setRestaurantFoodTypeCollection(Collection<RestaurantFoodType> restaurantFoodTypeCollection) {
        this.restaurantFoodTypeCollection = restaurantFoodTypeCollection;
    }

    @XmlTransient
    public Collection<RestaurantTimetable> getRestaurantTimetableCollection() {
        return restaurantTimetableCollection;
    }

    public void setRestaurantTimetableCollection(Collection<RestaurantTimetable> restaurantTimetableCollection) {
        this.restaurantTimetableCollection = restaurantTimetableCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantId != null ? restaurantId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.restaurantId == null && other.restaurantId != null) || (this.restaurantId != null && !this.restaurantId.equals(other.restaurantId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Restaurant[ restaurantId=" + restaurantId + " ]";
    }
    
}
