package com.example.costcontrol.API;
import com.example.costcontrol.API.Endpoint.TripEndpoint;
import com.example.costcontrol.Models.TripModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String URL_ROOT = "http://api.genialsaude.com.br/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL_ROOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void getTrip(long viagemId, final Callback<TripModel> callback) {
        TripEndpoint endPoint = retrofit.create(TripEndpoint.class);
        Call<TripModel> call = endPoint.getTrip(viagemId);
        call.enqueue(callback);
    }

    public static void getTripsByAccount(final Callback<ArrayList<TripModel>> callback){
        TripEndpoint endPoint = retrofit.create(TripEndpoint.class);
        Call<ArrayList<TripModel>> call = endPoint.getTripsByAccount();
        call.enqueue(callback);
    }

    public static void postTrips(TripModel trip, final Callback<TripModel> callback) {
        TripEndpoint endPoint = retrofit.create(TripEndpoint.class);
        Call<TripModel> call = endPoint.postTrips(trip);
        call.enqueue(callback);
    }
}
