package com.namestore.alicenote.connect.reponse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kienht on 11/5/16.
 */

public class RSPSignup extends RSPBase {

    @SerializedName("status")
    private int status;

    public int getStatus() {
        return status;
    }
}
