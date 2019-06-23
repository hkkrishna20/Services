package com.cgi.integration.models;


import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class DocumentModel implements Serializable {
private Date createdAt;
private Date updatedAt;

public Date getCreatedAt() {
return createdAt;
}

public void setCreatedAt(Date createdAt) throws ParseException {

this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
// createdAt;//new java.sql.Date(System.currentTimeMillis());
}

public Date getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
// this.updatedAt = updatedAt; //new java.sql.Date(System.currentTimeMillis());
this.updatedAt = new java.sql.Timestamp(System.currentTimeMillis());
}
}



