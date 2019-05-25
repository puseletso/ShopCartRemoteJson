package com.puseletsomaraba.shop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.puseletsomaraba.shop.internetConnection.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globals {

    private static Gson gson=new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit  retrofit = new Retrofit.Builder()
            .baseUrl("http://files.masoft.co.za/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static ApiInterface apiInterface = retrofit.create(ApiInterface.class);


}
