package com.namestore.alicenote.connect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.namestore.alicenote.data.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kienht on 11/4/16.
 */

public class ServiceGenerator {

    public static <S> S creatService(Class<S> serviceClass){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_SERVER)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        return retrofit.create(serviceClass);
    }

}
