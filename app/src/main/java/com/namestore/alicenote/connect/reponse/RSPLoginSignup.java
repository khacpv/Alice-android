package com.namestore.alicenote.connect.reponse;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kienht on 11/7/16.
 */

public class RSPLoginSignup {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("errors")
    @Expose
    private JsonObject errors;

    @SerializedName("token")
    @Expose
    private String token;

    public int getStatus() {
        return status;
    }

    public JsonObject getErrors() {
        return errors;
    }

    public String getToken() {
        return token;
    }
}
