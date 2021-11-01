package com.example.tp2.data;

import android.media.metrics.Event;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface SoaAPIService {
    @Headers("Content-Type: application/json")
    @POST("api/register")
    Call<SoaAPIResponse> register(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("api/login")
    Call<SoaAPIResponse> login(@Body User user);

    @Headers("Content-Type: application/json")
    @POST("api/event")
    Call<SoaAPIResponse> event(@Body Event event, @Header("Authorization") String token);

    @Headers("Content-Type: application/json")
    @PUT("api/refresh")
    Call<SoaAPIResponse> refresh_token(@Header("Authorization") String token);
}

