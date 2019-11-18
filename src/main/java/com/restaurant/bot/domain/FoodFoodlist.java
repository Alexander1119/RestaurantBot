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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "food_foodlist")
@NamedQueries({
    @NamedQuery(name = "FoodFoodlist.findAll", query = "SELECT f FROM FoodFoodlist f"),
    @NamedQuery(name = "FoodFoodlist.findByFoodFoodlistId", query = "SELECT f FROM FoodFoodlist f WHERE f.foodFoodlistId = :foodFoodlistId")})
public class FoodFoodlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_foodlist_id")
    private Integer foodFoodlistId;
    @JoinColumn(name = "id_food", referencedColumnName = "id_food")
    @ManyToOne(optional = false)
    private Food idFood;
    @JoinColumn(name = "foodlist_id", referencedColumnName = "foodlist_id")
    @ManyToOne(optional = false)
    private Foodlist foodlistId;

    public FoodFoodlist() {
    }

    public FoodFoodlist(Integer foodFoodlistId) {
        this.foodFoodlistId = foodFoodlistId;
    }

    public Integer getFoodFoodlistId() {
        return foodFoodlistId;
    }

    public void setFoodFoodlistId(Integer foodFoodlistId) {
        this.foodFoodlistId = foodFoodlistId;
    }

    public Food getIdFood() {
        return idFood;
    }

    public void setIdFood(Food idFood) {
        this.idFood = idFood;
    }

    public Foodlist getFoodlistId() {
        return foodlistId;
    }

    public void setFoodlistId(Foodlist foodlistId) {
        this.foodlistId = foodlistId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodFoodlistId != null ? foodFoodlistId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FoodFoodlist)) {
            return false;
        }
        FoodFoodlist other = (FoodFoodlist) object;
        if ((this.foodFoodlistId == null && other.foodFoodlistId != null) || (this.foodFoodlistId != null && !this.foodFoodlistId.equals(other.foodFoodlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.FoodFoodlist[ foodFoodlistId=" + foodFoodlistId + " ]";
    }
    
}
