package com.namestore.alicenote.connect.network.api;

import com.namestore.alicenote.connect.reponse.RSPBase;
import com.namestore.alicenote.connect.reponse.RSPLoginSignup;
import com.namestore.alicenote.data.Constants;
import com.namestore.alicenote.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by kienht on 11/3/16.
 */

public interface AliceApi {

    @POST(Constants.API_LOGIN)
    Call<RSPLoginSignup> login(@Body User user);

    @POST(Constants.API_SIGNUP)
    Call<RSPLoginSignup> signup(@Body User user);

    @POST(Constants.API_SOCIAL_LOGIN)
    Call<RSPLoginSignup> socialLogin(@Body User user);

}

