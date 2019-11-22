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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "restaurant_food_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RestaurantFoodType.findAll", query = "SELECT r FROM RestaurantFoodType r"),
    @NamedQuery(name = "RestaurantFoodType.findByRestaurantTypeFoodId", query = "SELECT r FROM RestaurantFoodType r WHERE r.restaurantTypeFoodId = :restaurantTypeFoodId")})
public class RestaurantFoodType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_type_food_id")
    private Integer restaurantTypeFoodId;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;
    @JoinColumn(name = "id_type_food", referencedColumnName = "id_type_food")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TypeFood idTypeFood;

    public RestaurantFoodType() {
    }

    public RestaurantFoodType(Integer restaurantTypeFoodId) {
        this.restaurantTypeFoodId = restaurantTypeFoodId;
    }

    public Integer getRestaurantTypeFoodId() {
        return restaurantTypeFoodId;
    }

    public void setRestaurantTypeFoodId(Integer restaurantTypeFoodId) {
        this.restaurantTypeFoodId = restaurantTypeFoodId;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    public TypeFood getIdTypeFood() {
        return idTypeFood;
    }

    public void setIdTypeFood(TypeFood idTypeFood) {
        this.idTypeFood = idTypeFood;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (restaurantTypeFoodId != null ? restaurantTypeFoodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantFoodType)) {
            return false;
        }
        RestaurantFoodType other = (RestaurantFoodType) object;
        if ((this.restaurantTypeFoodId == null && other.restaurantTypeFoodId != null) || (this.restaurantTypeFoodId != null && !this.restaurantTypeFoodId.equals(other.restaurantTypeFoodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.RestaurantFoodType[ restaurantTypeFoodId=" + restaurantTypeFoodId + " ]";
    }
    
}
