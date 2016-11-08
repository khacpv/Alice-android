package com.namestore.alicenote.connect.reponse;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kienht on 11/4/16.
 */

public class RSPBase implements Serializable {

    @SerializedName("status")
    @Expose
    private int status;

    public int getStatus() {
        return status;
    }

    @SerializedName("errors")
    @Expose
    private JsonObject errors;

    public JsonObject getErrors() {
        return errors;
    }

}
