package com.billt.core.invoicereceiver.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import java.util.Date;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String mobile;
    private Date creationDate = new Date();
   // @Email
    private String email;
    @Transient
    private String password;
    private String fullname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullName() {
        return fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobie() {
        return mobile;
    }

    public void setMobile(String value) {
        this.mobile = value;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public String toString(){
    return "id= "+id +" full name= "+fullname+" email = "+email+" mobile "+mobile+ " creation date = "+ creationDate;
    }

}