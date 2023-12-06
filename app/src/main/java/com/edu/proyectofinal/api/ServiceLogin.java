package com.edu.proyectofinal.api;

import com.edu.proyectofinal.model.Loger;
import com.edu.proyectofinal.model.ResponseCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accessLogin(@Body Loger login);
}
