package com.jisna.mvvm_livedata;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageAdapter adapter;


    List<ResponseClass> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageViewModel model = ViewModelProviders.of(this).get(ImageViewModel.class);
        final SpotsDialog  dialog = new SpotsDialog(this);
        if (CheckNetwork.isInternetconnected(this)) {

            dialog.show();
            model.getImages().observe(this, new Observer<List<ResponseClass>>() {
                @Override
                public void onChanged(@Nullable List<ResponseClass> imageList) {
                    dialog.dismiss();
                    adapter = new ImageAdapter(MainActivity.this, imageList);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
        else{
            dialog.dismiss();
            Toast.makeText(MainActivity.this,"Please Connect to the network",Toast.LENGTH_SHORT).show();
        }
    }

}
