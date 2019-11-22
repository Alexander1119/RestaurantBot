/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restaurant.bot.domain;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "r_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RUser.findAll", query = "SELECT r FROM RUser r"),
    @NamedQuery(name = "RUser.findByUserId", query = "SELECT r FROM RUser r WHERE r.userId = :userId"),
    @NamedQuery(name = "RUser.findByBotUserId", query = "SELECT r FROM RUser r WHERE r.botUserId = :botUserId"),
    @NamedQuery(name = "RUser.findByTxUser", query = "SELECT r FROM RUser r WHERE r.txUser = :txUser"),
    @NamedQuery(name = "RUser.findByTxHost", query = "SELECT r FROM RUser r WHERE r.txHost = :txHost"),
    @NamedQuery(name = "RUser.findByTxDate", query = "SELECT r FROM RUser r WHERE r.txDate = :txDate")})
public class RUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "bot_user_id")
    private String botUserId;
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

    public RUser() {
    }

    public RUser(Integer userId) {
        this.userId = userId;
    }

    public RUser(Integer userId, String botUserId, String txUser, String txHost, Date txDate) {
        this.userId = userId;
        this.botUserId = botUserId;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(String botUserId) {
        this.botUserId = botUserId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RUser)) {
            return false;
        }
        RUser other = (RUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.RUser[ userId=" + userId + " ]";
    }
    
}
