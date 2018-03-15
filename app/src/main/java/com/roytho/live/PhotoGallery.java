package com.roytho.live;

import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.roytho.live.home.news.ReadNews;
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
