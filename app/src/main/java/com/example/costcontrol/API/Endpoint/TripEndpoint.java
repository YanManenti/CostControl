package com.example.costcontrol.API.Endpoint;

import com.example.costcontrol.Models.TripModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TripEndpoint {

    @GET("/api/listar/viagem")
    Call<TripModel> getTrip(@Query("viagemId") long viagemId);

    @GET("/api/listar/viagem/conta?contaId=128113")
    Call<ArrayList<TripModel>> getTripsByAccount();

    @POST("/api/cadastro/viagem")
    Call<TripModel> postTrips(@Body TripModel trip);

}
