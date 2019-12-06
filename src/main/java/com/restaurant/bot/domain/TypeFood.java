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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "type_food")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeFood.findAll", query = "SELECT t FROM TypeFood t"),
    @NamedQuery(name = "TypeFood.findByTypefoodId", query = "SELECT t FROM TypeFood t WHERE t.typefoodId = :typefoodId"),
    @NamedQuery(name = "TypeFood.findByTypefoodName", query = "SELECT t FROM TypeFood t WHERE t.typefoodName = :typefoodName")})
public class TypeFood implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "typefood_id")
    private Integer typefoodId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "typefood_name")
    private String typefoodName;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;

    public TypeFood() {
    }

    public TypeFood(Integer typefoodId) {
        this.typefoodId = typefoodId;
    }

    public TypeFood(Integer typefoodId, String typefoodName) {
        this.typefoodId = typefoodId;
        this.typefoodName = typefoodName;
    }

    public Integer getTypefoodId() {
        return typefoodId;
    }

    public void setTypefoodId(Integer typefoodId) {
        this.typefoodId = typefoodId;
    }

    public String getTypefoodName() {
        return typefoodName;
    }

    public void setTypefoodName(String typefoodName) {
        this.typefoodName = typefoodName;
    }

    public Restaurant getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Restaurant restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typefoodId != null ? typefoodId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeFood)) {
            return false;
        }
        TypeFood other = (TypeFood) object;
        if ((this.typefoodId == null && other.typefoodId != null) || (this.typefoodId != null && !this.typefoodId.equals(other.typefoodId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.TypeFood[ typefoodId=" + typefoodId + " ]";
    }
    
}
