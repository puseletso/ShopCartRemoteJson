package com.puseletsomaraba.shop.internetConnection;

import com.puseletsomaraba.shop.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("food.json")
    Call<List<Product>> getFoodList();

    @GET("perfume.json")
    Call<List<Product>> getPerfumeList();

    @GET("medicine.json")
    Call<List<Product>> getMedicineList();

    @GET("music.json")
    Call<List<Product>> getMusicList();

    @GET("books.json")
    Call<List<Product>> getBookList();



}
