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
@Table(name = "chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByChatId", query = "SELECT c FROM Chat c WHERE c.chatId = :chatId"),
    @NamedQuery(name = "Chat.findByConversationId", query = "SELECT c FROM Chat c WHERE c.conversationId = :conversationId"),
    @NamedQuery(name = "Chat.findByMessageId", query = "SELECT c FROM Chat c WHERE c.messageId = :messageId"),
    @NamedQuery(name = "Chat.findByInMessage", query = "SELECT c FROM Chat c WHERE c.inMessage = :inMessage"),
    @NamedQuery(name = "Chat.findByOutMessage", query = "SELECT c FROM Chat c WHERE c.outMessage = :outMessage"),
    @NamedQuery(name = "Chat.findByMessageDate", query = "SELECT c FROM Chat c WHERE c.messageDate = :messageDate"),
    @NamedQuery(name = "Chat.findByTxUser", query = "SELECT c FROM Chat c WHERE c.txUser = :txUser"),
    @NamedQuery(name = "Chat.findByTxHost", query = "SELECT c FROM Chat c WHERE c.txHost = :txHost"),
    @NamedQuery(name = "Chat.findByTxDate", query = "SELECT c FROM Chat c WHERE c.txDate = :txDate")})
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chat_id")
    private Integer chatId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conversation_id")
    private int conversationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "message_id")
    private int messageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "in_message")
    private String inMessage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "out_message")
    private String outMessage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "message_date")
    @Temporal(TemporalType.DATE)
    private Date messageDate;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cpuser userId;

    public Chat() {
    }

    public Chat(Integer chatId) {
        this.chatId = chatId;
    }

    public Chat(Integer chatId, int conversationId, int messageId, String inMessage, String outMessage, Date messageDate, String txUser, String txHost, Date txDate) {
        this.chatId = chatId;
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.inMessage = inMessage;
        this.outMessage = outMessage;
        this.messageDate = messageDate;
        this.txUser = txUser;
        this.txHost = txHost;
        this.txDate = txDate;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
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

    public Cpuser getUserId() {
        return userId;
    }

    public void setUserId(Cpuser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatId != null ? chatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.chatId == null && other.chatId != null) || (this.chatId != null && !this.chatId.equals(other.chatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.restaurant.bot.domain.Chat[ chatId=" + chatId + " ]";
    }
    
}
