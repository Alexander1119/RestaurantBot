/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "restaurant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurant.findAll", query = "SELECT r FROM Restaurant r"),
    @NamedQuery(name = "Restaurant.findByRestaurantId", query = "SELECT r FROM Restaurant r WHERE r.restaurantId = :restaurantId"),
    @NamedQuery(name = "Restaurant.findByRestaurantName", query = "SELECT r FROM Restaurant r WHERE r.restaurantName = :restaurantName"),
    @NamedQuery(name = "Restaurant.findByZone", query = "SELECT r FROM Restaurant r WHERE r.zone = :zone"),
    @NamedQuery(name = "Restaurant.findByCity", query = "SELECT r FROM Restaurant r WHERE r.city = :city"),
    @NamedQuery(name = "Restaurant.findByStreet", query = "SELECT r FROM Restaurant r WHERE r.street = :street"),
    @NamedQuery(name = "Restaurant.findByImages", query = "SELECT r FROM Restaurant r WHERE r.images = :images"),
    @NamedQuery(name = "Restaurant.findByLongitude", query = "SELECT r FROM Restaurant r WHERE r.longitude = :longitude"),
    @NamedQuery(name = "Restaurant.findByLatitude", query = "SELECT r FROM Restaurant r WHERE r.latitude = :latitude"),
    @NamedQuery(name = "Restaurant.findByStatus", query = "SELECT r FROM Restaurant r WHERE r.status = :status"),
    @NamedQuery(name = "Restaurant.findByTxUser", query = "SELECT r FROM Restaurant r WHERE r.txUser = :txUser"),
    @NamedQuery(name = "Restaurant.findByTxHost", query = "SELECT r FROM Restaurant r WHERE r.txHost = :txHost"),
    @NamedQuery(name = "Restaurant.findByTxDate", query = "SELECT r FROM Restaurant r WHERE r.txDate = :txDate")})
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "restaurant_id")
    private Integer restaurantId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "zone")
    private String zone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "street")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "images")
    private String images;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tx_user")
    private String txUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "tx_host")
    private String txHost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tx_date")
    @Temporal(TemporalType.DATE)
    private Date txDate;
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Person personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Collection<TypeFood> typeFoodCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurantId", fetch = FetchType.LAZY)
    private Collection<Timetable> timetableCollection;

    public Restaurant() {
    }

    public Restaurant(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Restaurant(Integer restaurantId, String restaurantName, String zone, String city, String street, String images, BigDecimal longitude, BigDecimal latitude, int status, String txUser, String txHost, Date txDate) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.zone = zone;
        this.city = city;
        this.street = street;
        this.images = images;
        this.longitude = longitude;
        this.latitude = latitude;
        this.status = status;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTxUser() {
        return txUser;
    }

    public void setTxUser(String txUser) {
        this.txUser = txUser;
    }

    public String getTxHost() {
        return txHost;
    }

    public void setTxHost(String txHost) {
        this.txHost = txHost;
    }

    public Date getTxDate() {
        return txDate;
    }

    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    public Person getPersonId() {
        return personId;
    }

    public void setPersonId(Person personId) {
        this.personId = personId;
    }

    @XmlTransient
    public Collection<TypeFood> getTypeFoodCollection() {
        return typeFoodCollection;
    }

    public void setTypeFoodCollection(Collection<TypeFood> typeFoodCollection) {
        this.typeFoodCollection = typeFoodCollection;
    }

    @XmlTransient
    public Collection<Timetable> getTimetableCollection() {
        return timetableCollection;
    }

    public void setTimetableCollection(Collection<Timetable> timetableCollection) {
        this.timetableCollection = timetableCollection;
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
