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
@Table(name = "food_list")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FoodList.findAll", query = "SELECT f FROM FoodList f"),
    @NamedQuery(name = "FoodList.findByFoodlistId", query = "SELECT f FROM FoodList f WHERE f.foodlistId = :foodlistId"),
    @NamedQuery(name = "FoodList.findByStatus", query = "SELECT f FROM FoodList f WHERE f.status = :status")})
public class FoodList implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "foodlist_id")
    private Integer foodlistId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "timetable_id", referencedColumnName = "timetable_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Timetable timetableId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodlistId", fetch = FetchType.LAZY)
    private Collection<Food> foodCollection;

    public FoodList() {
    }

    public FoodList(Integer foodlistId) {
        this.foodlistId = foodlistId;
    }

    public FoodList(Integer foodlistId, int status) {
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

    public Timetable getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Timetable timetableId) {
        this.timetableId = timetableId;
    }

    @XmlTransient
    public Collection<Food> getFoodCollection() {
        return foodCollection;
    }

    public void setFoodCollection(Collection<Food> foodCollection) {
        this.foodCollection = foodCollection;
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
        if (!(object instanceof FoodList)) {
            return false;
        }
        FoodList other = (FoodList) object;
        if ((this.foodlistId == null && other.foodlistId != null) || (this.foodlistId != null && !this.foodlistId.equals(other.foodlistId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.FoodList[ foodlistId=" + foodlistId + " ]";
    }
    
}
