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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "type_food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeFood.findAll", query = "SELECT t FROM TypeFood t"),
    @NamedQuery(name = "TypeFood.findByIdTypeFood", query = "SELECT t FROM TypeFood t WHERE t.idTypeFood = :idTypeFood"),
    @NamedQuery(name = "TypeFood.findByNameType", query = "SELECT t FROM TypeFood t WHERE t.nameType = :nameType")})
public class TypeFood implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_type_food")
    private Integer idTypeFood;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name_type")
    private String nameType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTypeFood", fetch = FetchType.LAZY)
    private Collection<RestaurantFoodType> restaurantFoodTypeCollection;

    public TypeFood() {
    }

    public TypeFood(Integer idTypeFood) {
        this.idTypeFood = idTypeFood;
    }

    public TypeFood(Integer idTypeFood, String nameType) {
        this.idTypeFood = idTypeFood;
        this.nameType = nameType;
    }

    public Integer getIdTypeFood() {
        return idTypeFood;
    }

    public void setIdTypeFood(Integer idTypeFood) {
        this.idTypeFood = idTypeFood;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @XmlTransient
    public Collection<RestaurantFoodType> getRestaurantFoodTypeCollection() {
        return restaurantFoodTypeCollection;
    }

    public void setRestaurantFoodTypeCollection(Collection<RestaurantFoodType> restaurantFoodTypeCollection) {
        this.restaurantFoodTypeCollection = restaurantFoodTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTypeFood != null ? idTypeFood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeFood)) {
            return false;
        }
        TypeFood other = (TypeFood) object;
        if ((this.idTypeFood == null && other.idTypeFood != null) || (this.idTypeFood != null && !this.idTypeFood.equals(other.idTypeFood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.TypeFood[ idTypeFood=" + idTypeFood + " ]";
    }
    
}
