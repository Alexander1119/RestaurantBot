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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "place")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findByPlaceId", query = "SELECT p FROM Place p WHERE p.placeId = :placeId"),
    @NamedQuery(name = "Place.findByNamePlace", query = "SELECT p FROM Place p WHERE p.namePlace = :namePlace")})
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "place_id")
    private Integer placeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_place")
    private String namePlace;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "placeId", fetch = FetchType.LAZY)
    private Collection<Restaurant> restaurantCollection;
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Zone zoneId;

    public Place() {
    }

    public Place(Integer placeId) {
        this.placeId = placeId;
    }

    public Place(Integer placeId, String namePlace) {
        this.placeId = placeId;
        this.namePlace = namePlace;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getNamePlace() {
        return namePlace;
    }

    public void setNamePlace(String namePlace) {
        this.namePlace = namePlace;
    }

    @XmlTransient
    public Collection<Restaurant> getRestaurantCollection() {
        return restaurantCollection;
    }

    public void setRestaurantCollection(Collection<Restaurant> restaurantCollection) {
        this.restaurantCollection = restaurantCollection;
    }

    public Zone getZoneId() {
        return zoneId;
    }

    public void setZoneId(Zone zoneId) {
        this.zoneId = zoneId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placeId != null ? placeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.placeId == null && other.placeId != null) || (this.placeId != null && !this.placeId.equals(other.placeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Place[ placeId=" + placeId + " ]";
    }
    
}
