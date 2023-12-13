package com.edu.proyectofinal.api;

import com.edu.proyectofinal.Cart;
import com.edu.proyectofinal.model.Loger;
import com.edu.proyectofinal.model.Products;
import com.edu.proyectofinal.model.Register;
import com.edu.proyectofinal.model.ResponseCredentials;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceLogin {
    @POST("login")
    public Call<ResponseCredentials> accessLogin(@Body Loger login);

    @POST("users")
    public Call<String> registrousuario(@Body Register register);

    @GET("products")
    Call<List<Products>> getProducts();
}
