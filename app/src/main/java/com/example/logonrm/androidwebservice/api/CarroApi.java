package com.example.logonrm.androidwebservice.api;

import com.example.logonrm.androidwebservice.model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by logonrm on 10/06/2017.
 */

public interface CarroApi {

    @Headers("Content-Type: application/json")
    @GET("/")
    public Call<List<Carro>> listarTodos();

}
