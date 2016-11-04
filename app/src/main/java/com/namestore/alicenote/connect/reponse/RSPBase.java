package com.namestore.alicenote.connect.reponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kienht on 11/4/16.
 */

public class RSPBase implements Serializable{

        @SerializedName("status")
        private int status;

}
