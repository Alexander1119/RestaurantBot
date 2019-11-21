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
@Table(name = "street")
@NamedQueries({
    @NamedQuery(name = "Street.findAll", query = "SELECT s FROM Street s"),
    @NamedQuery(name = "Street.findByStreetId", query = "SELECT s FROM Street s WHERE s.streetId = :streetId"),
    @NamedQuery(name = "Street.findByNameStreet", query = "SELECT s FROM Street s WHERE s.nameStreet = :nameStreet")})
public class Street implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "street_id")
    private Integer streetId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name_street")
    private String nameStreet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "streetId", fetch = FetchType.LAZY)
    private Collection<Zone> zoneCollection;

    public Street() {
    }

    public Street(Integer streetId) {
        this.streetId = streetId;
    }

    public Street(Integer streetId, String nameStreet) {
        this.streetId = streetId;
        this.nameStreet = nameStreet;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getNameStreet() {
        return nameStreet;
    }

    public void setNameStreet(String nameStreet) {
        this.nameStreet = nameStreet;
    }

    public Collection<Zone> getZoneCollection() {
        return zoneCollection;
    }

    public void setZoneCollection(Collection<Zone> zoneCollection) {
        this.zoneCollection = zoneCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (streetId != null ? streetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Street)) {
            return false;
        }
        Street other = (Street) object;
        if ((this.streetId == null && other.streetId != null) || (this.streetId != null && !this.streetId.equals(other.streetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Street[ streetId=" + streetId + " ]";
    }
    
}
