/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "timetable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timetable.findAll", query = "SELECT t FROM Timetable t"),
    @NamedQuery(name = "Timetable.findByTimetableId", query = "SELECT t FROM Timetable t WHERE t.timetableId = :timetableId"),
    @NamedQuery(name = "Timetable.findByDay", query = "SELECT t FROM Timetable t WHERE t.day = :day"),
    @NamedQuery(name = "Timetable.findByOpeningTime", query = "SELECT t FROM Timetable t WHERE t.openingTime = :openingTime"),
    @NamedQuery(name = "Timetable.findByClosingTime", query = "SELECT t FROM Timetable t WHERE t.closingTime = :closingTime"),
    @NamedQuery(name = "Timetable.findByStatus", query = "SELECT t FROM Timetable t WHERE t.status = :status")})
public class Timetable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "timetable_id")
    private Integer timetableId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "day")
    private String day;
    @Basic(optional = false)
    @NotNull
    @Column(name = "opening_time")
    @Temporal(TemporalType.TIME)
    private Date openingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "closing_time")
    @Temporal(TemporalType.TIME)
    private Date closingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timetableId", fetch = FetchType.LAZY)
    private Collection<FoodList> foodListCollection;
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Restaurant restaurantId;

    public Timetable() {
    }

    public Timetable(Integer timetableId) {
        this.timetableId = timetableId;
    }

    public Timetable(Integer timetableId, String day, Date openingTime, Date closingTime, int status) {
        this.timetableId = timetableId;
        this.day = day;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.status = status;
    }

    public Integer getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Integer timetableId) {
        this.timetableId = timetableId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<FoodList> getFoodListCollection() {
        return foodListCollection;
    }

    public void setFoodListCollection(Collection<FoodList> foodListCollection) {
        this.foodListCollection = foodListCollection;
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
        hash += (timetableId != null ? timetableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Timetable)) {
            return false;
        }
        Timetable other = (Timetable) object;
        if ((this.timetableId == null && other.timetableId != null) || (this.timetableId != null && !this.timetableId.equals(other.timetableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Timetable[ timetableId=" + timetableId + " ]";
    }
    
}
