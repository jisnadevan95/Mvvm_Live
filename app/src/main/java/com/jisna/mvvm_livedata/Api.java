package com.jisna.mvvm_livedata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://jsonplaceholder.typicode.com/";

    @GET("photos")
    Call<List<ResponseClass>> getImages();
}
