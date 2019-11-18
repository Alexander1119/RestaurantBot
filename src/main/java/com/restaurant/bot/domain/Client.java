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

/**
 *
 * @author ALEXANDER
 */
@Entity
@Table(name = "client")
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByClientId", query = "SELECT c FROM Client c WHERE c.clientId = :clientId"),
    @NamedQuery(name = "Client.findByBotUserId", query = "SELECT c FROM Client c WHERE c.botUserId = :botUserId"),
    @NamedQuery(name = "Client.findByConvesacionId", query = "SELECT c FROM Client c WHERE c.convesacionId = :convesacionId"),
    @NamedQuery(name = "Client.findByLastMessageSend", query = "SELECT c FROM Client c WHERE c.lastMessageSend = :lastMessageSend"),
    @NamedQuery(name = "Client.findByLastMessageReceiv", query = "SELECT c FROM Client c WHERE c.lastMessageReceiv = :lastMessageReceiv"),
    @NamedQuery(name = "Client.findByTxUser", query = "SELECT c FROM Client c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "Client.findByTxHost", query = "SELECT c FROM Client c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "Client.findByTxDate", query = "SELECT c FROM Client c WHERE c.txDate = :txDate")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "client_id")
    private Integer clientId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bot_user_id")
    private int botUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "convesacion_id")
    private int convesacionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "last_message_send")
    private String lastMessageSend;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "last_message_receiv")
    private String lastMessageReceiv;
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
    @ManyToOne(optional = false)
    private Person personId;

    public Client() {
    }

    public Client(Integer clientId) {
        this.clientId = clientId;
    }

    public Client(Integer clientId, int botUserId, int convesacionId, String lastMessageSend, String lastMessageReceiv, String txUser, String txHost, Date txDate) {
        this.clientId = clientId;
        this.botUserId = botUserId;
        this.convesacionId = convesacionId;
        this.lastMessageSend = lastMessageSend;
        this.lastMessageReceiv = lastMessageReceiv;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public int getBotUserId() {
        return botUserId;
    }

    public void setBotUserId(int botUserId) {
        this.botUserId = botUserId;
    }

    public int getConvesacionId() {
        return convesacionId;
    }

    public void setConvesacionId(int convesacionId) {
        this.convesacionId = convesacionId;
    }

    public String getLastMessageSend() {
        return lastMessageSend;
    }

    public void setLastMessageSend(String lastMessageSend) {
        this.lastMessageSend = lastMessageSend;
    }

    public String getLastMessageReceiv() {
        return lastMessageReceiv;
    }

    public void setLastMessageReceiv(String lastMessageReceiv) {
        this.lastMessageReceiv = lastMessageReceiv;
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
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Client[ clientId=" + clientId + " ]";
    }
    
}
