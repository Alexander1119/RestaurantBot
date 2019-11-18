/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "food")
@NamedQueries({
    @NamedQuery(name = "Food.findAll", query = "SELECT f FROM Food f"),
    @NamedQuery(name = "Food.findByIdFood", query = "SELECT f FROM Food f WHERE f.idFood = :idFood"),
    @NamedQuery(name = "Food.findByNameFood", query = "SELECT f FROM Food f WHERE f.nameFood = :nameFood"),
    @NamedQuery(name = "Food.findByCost", query = "SELECT f FROM Food f WHERE f.cost = :cost"),
    @NamedQuery(name = "Food.findByStatus", query = "SELECT f FROM Food f WHERE f.status = :status")})
public class Food implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_food")
    private Integer idFood;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_food")
    private String nameFood;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cost")
    private BigDecimal cost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFood")
    private Collection<FoodFoodlist> foodFoodlistCollection;

    public Food() {
    }

    public Food(Integer idFood) {
        this.idFood = idFood;
    }

    public Food(Integer idFood, String nameFood, BigDecimal cost, int status) {
        this.idFood = idFood;
        this.nameFood = nameFood;
        this.cost = cost;
        this.status = status;
    }

    public Integer getIdFood() {
        return idFood;
    }

    public void setIdFood(Integer idFood) {
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
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

    public Collection<FoodFoodlist> getFoodFoodlistCollection() {
        return foodFoodlistCollection;
    }

    public void setFoodFoodlistCollection(Collection<FoodFoodlist> foodFoodlistCollection) {
        this.foodFoodlistCollection = foodFoodlistCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFood != null ? idFood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Food)) {
            return false;
        }
        Food other = (Food) object;
        if ((this.idFood == null && other.idFood != null) || (this.idFood != null && !this.idFood.equals(other.idFood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Food[ idFood=" + idFood + " ]";
    }
    
}
