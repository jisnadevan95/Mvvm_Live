package com.jisna.mvvm_livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;



import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageViewModel extends ViewModel {

    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<ResponseClass>> imageLists;

    //we will call this method to get the data
    public LiveData<List<ResponseClass>> getImages() {
        //if the list is null
        if (imageLists == null) {
            imageLists = new MutableLiveData<List<ResponseClass>>();
            //we will load it asynchronously from server in this method

            loadImages();

        }

        //finally we will return the list
        return imageLists;
    }




    //This method is using Retrofit to get the JSON data from URL
   private void loadImages() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<ResponseClass>> call = api.getImages();


        call.enqueue(new Callback<List<ResponseClass>>() {
            @Override
            public void onResponse(Call<List<ResponseClass>> call, Response<List<ResponseClass>> response) {

                //finally we are setting the list to our MutableLiveData
                imageLists.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseClass>> call, Throwable t) {

            }
        });
    }

}
