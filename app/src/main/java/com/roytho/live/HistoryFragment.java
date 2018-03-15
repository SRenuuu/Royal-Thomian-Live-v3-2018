package com.roytho.live;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TabHost;

import com.roytho.live.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    WebView wvEvents;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        TabHost tabs = v.findViewById(R.id.historytabs); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();

        TabHost.TabSpec spec = tabs.newTabSpec("Intro");

        spec.setContent(R.id.tab1);
        spec.setIndicator("Intro");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Shield");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Shield");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Atmosphere");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Atmosphere");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Venues");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Venues");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("Archives");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Archives");
        tabs.addTab(spec);

        wvEvents = v.findViewById(R.id.wvEvents);
        wvEvents.loadUrl("file:///android_asset/past.html");

        return v;
    }

}