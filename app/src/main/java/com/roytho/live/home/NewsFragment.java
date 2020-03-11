package com.roytho.live.home;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.roytho.live.R;
import com.roytho.live.home.news.ReadNews;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    ReadNews readRSS;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = v.findViewById(R.id.news_list);

        readRSS = new ReadNews(getActivity(), recyclerView, "http://stcmount.edu.lk/app/get/get.php?type=roythonews");
        readRSS.execute();

        return v;
    }

}
