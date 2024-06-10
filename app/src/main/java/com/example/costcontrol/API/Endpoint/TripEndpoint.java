package com.example.costcontrol.API.Endpoint;

import com.example.costcontrol.Models.TripModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TripEndpoint {

    @GET("/api/listar/aluno")
    Call<ArrayList<TripModel>> getTrips();

    @POST("/api/cadastro/aluno")
    Call<TripModel> postTrips(@Body TripModel trip);

}
