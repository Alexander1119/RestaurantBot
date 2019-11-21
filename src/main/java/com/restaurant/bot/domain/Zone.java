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

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "zone")
@NamedQueries({
    @NamedQuery(name = "Zone.findAll", query = "SELECT z FROM Zone z"),
    @NamedQuery(name = "Zone.findByZoneId", query = "SELECT z FROM Zone z WHERE z.zoneId = :zoneId"),
    @NamedQuery(name = "Zone.findByNameZone", query = "SELECT z FROM Zone z WHERE z.nameZone = :nameZone")})
public class Zone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "zone_id")
    private Integer zoneId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_zone")
    private String nameZone;
    @JoinColumn(name = "street_id", referencedColumnName = "street_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Street streetId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zoneId", fetch = FetchType.LAZY)
    private Collection<Place> placeCollection;

    public Zone() {
    }

    public Zone(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Zone(Integer zoneId, String nameZone) {
        this.zoneId = zoneId;
        this.nameZone = nameZone;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public String getNameZone() {
        return nameZone;
    }

    public void setNameZone(String nameZone) {
        this.nameZone = nameZone;
    }

    public Street getStreetId() {
        return streetId;
    }

    public void setStreetId(Street streetId) {
        this.streetId = streetId;
    }

    public Collection<Place> getPlaceCollection() {
        return placeCollection;
    }

    public void setPlaceCollection(Collection<Place> placeCollection) {
        this.placeCollection = placeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zoneId != null ? zoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zone)) {
            return false;
        }
        Zone other = (Zone) object;
        if ((this.zoneId == null && other.zoneId != null) || (this.zoneId != null && !this.zoneId.equals(other.zoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Zone[ zoneId=" + zoneId + " ]";
    }
    
}
