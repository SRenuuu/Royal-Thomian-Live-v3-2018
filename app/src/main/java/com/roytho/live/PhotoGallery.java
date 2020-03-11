package com.roytho.live;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.roytho.live.photos.LoadPhotos;

public class PhotoGallery extends AppCompatActivity {

    RecyclerView recyclerView;
    LoadPhotos loadPhotos;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        recyclerView = findViewById(R.id.photo_list);
        swipeRefreshLayout = findViewById(R.id.photos_swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPhotos = new LoadPhotos(PhotoGallery.this, recyclerView);
                loadPhotos.execute();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        loadPhotos = new LoadPhotos(PhotoGallery.this, recyclerView);
        loadPhotos.execute();
    }
}
