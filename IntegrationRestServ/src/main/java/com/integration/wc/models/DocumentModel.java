package com.integration.wc.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class DocumentModel implements Serializable {
  @Column(name ="createdAt",nullable = true, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @Temporal(TemporalType.TIMESTAMP) 
  @CreatedDate
    private Date createdAt;

    @Column(name ="updatedAt",nullable = true,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP) 
    @LastModifiedDate
    private Date updatedAt;



public Date getCreatedAt() {
return createdAt;
}

public void setCreatedAt(Timestamp createdAt) throws ParseException {

this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
// createdAt;//new java.sql.Date(System.currentTimeMillis());
}

public Date getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(Timestamp updatedAt) {
// this.updatedAt = updatedAt; //new java.sql.Date(System.currentTimeMillis());
this.updatedAt = new java.sql.Timestamp(System.currentTimeMillis());
}
}