package com.namestore.alicenote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by kienht on 11/4/16.
 */

public class User {

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("password_hash")
    @Expose
    public String password_hash;

    @SerializedName("first_name")
    @Expose
    public String first_name;

    @SerializedName("last_name")
    @Expose
    public String last_name;


    @SerializedName("gender")
    @Expose
    public int gender;

    @SerializedName("telephone")
    @Expose
    public int telephone;

    @SerializedName("id")
    @Expose
    public String id;

    public int getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getTelephone() {
        return telephone;
    }


}