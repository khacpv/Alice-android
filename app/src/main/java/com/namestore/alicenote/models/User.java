package com.namestore.alicenote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by kienht on 11/4/16.
 */

public class User implements Serializable {

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("password_hash")
    @Expose
    public String passwordHash;

    @SerializedName("first_name")
    @Expose
    public String firstName;

    @SerializedName("last_name")
    @Expose
    public String lastName;


    @SerializedName("gender")
    @Expose
    public int gender;

    @SerializedName("telephone")
    @Expose
    public String telephone;

    @SerializedName("id")
    @Expose
    public String id;

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTelephone() {
        return telephone;
    }


}