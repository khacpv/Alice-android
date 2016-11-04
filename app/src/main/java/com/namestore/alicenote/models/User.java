package com.namestore.alicenote.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

}
