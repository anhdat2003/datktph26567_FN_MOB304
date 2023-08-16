package com.example.datktph26567_fn_mob304;

import com.example.datktph26567_fn_mob304.Model.Giay;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class RetrofitClient {
    private static final String BoseUrl = "https://64d76cfd2a017531bc133ac5.mockapi.io/THI/";
    public static ThiService getService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BoseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ThiService.class);
    }
}
interface ThiService{
    @GET("datktph26567_giay")
    Call<List<Giay>> getListLover();
    @POST("datktph26567_giay")
    Call<Giay> addLover(@Body Giay giay);
    @PUT("datktph26567_giay/{id}")
    Call<Giay> updateLover(@Path("id") String id, @Body Giay giay);
    @DELETE("datktph26567_giay/{id}")
    Call<Void> deleteLover(@Path("id") String id);
}
