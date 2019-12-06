/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByFoodId", query = "SELECT f FROM Food f WHERE f.foodId = :foodId"),
    @NamedQuery(name = "Food.findByFoodName", query = "SELECT f FROM Food f WHERE f.foodName = :foodName"),
    @NamedQuery(name = "Food.findByCost", query = "SELECT f FROM Food f WHERE f.cost = :cost"),
    @NamedQuery(name = "Food.findByStatus", query = "SELECT f FROM Food f WHERE f.status = :status")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "food_id")
    private Integer foodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "food_name")
    private String foodName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "foodlist_id", referencedColumnName = "foodlist_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FoodList foodlistId;

    public Food() {
    }

    public Food(Integer foodId) {
        this.foodId = foodId;
    }

    public Food(Integer foodId, String foodName, BigDecimal cost, int status) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.cost = cost;
        this.status = status;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FoodList getFoodlistId() {
        return foodlistId;
    }

    public void setFoodlistId(FoodList foodlistId) {
        this.foodlistId = foodlistId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foodId != null ? foodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.foodId == null && other.foodId != null) || (this.foodId != null && !this.foodId.equals(other.foodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Food[ foodId=" + foodId + " ]";
    }
    
}
