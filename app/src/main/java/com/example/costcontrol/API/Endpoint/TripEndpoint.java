package com.example.costcontrol.API.Endpoint;

import com.example.costcontrol.Models.TripModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TripEndpoint {

    @GET("/api/listar/viagem")
    Call<TripModel> getTrip();

    @GET("/api/listar/viagem/conta")
    Call<ArrayList<TripModel>> getTripsByAccount();

    @POST("/api/cadastro/viagem")
    Call<TripModel> postTrips(@Body TripModel trip);

}
