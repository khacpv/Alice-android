package com.namestore.alicenote.connect.network.api;

import com.namestore.alicenote.connect.reponse.RSPLogin;
import com.namestore.alicenote.connect.reponse.RSPSignup;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by kienht on 11/3/16.
 */

public interface AliceApi {

    @POST(Constants.API_LOGIN)
    Call<RSPLogin> login(@Body User user);


    @POST(Constants.API_SIGNUP)
    Call<RSPSignup> signup(@Body User user);

}

