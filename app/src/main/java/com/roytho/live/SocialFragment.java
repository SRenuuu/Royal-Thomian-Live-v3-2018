package com.roytho.live;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends Fragment {


    WebView wvFB, wvTW;

    public SocialFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_social, container, false);

        TabHost tabs=v.findViewById(R.id.socialtabs); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("Twitter");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Twitter");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("Instagram");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Instagram");
        tabs.addTab(spec);

        wvFB = v.findViewById(R.id.wvFB);
        wvFB.getSettings().setJavaScriptEnabled(true);
        wvFB.getSettings().setDomStorageEnabled(true);
        wvFB.loadUrl("file:///android_asset/instagram.html");

        wvTW = v.findViewById(R.id.wvTW);
        wvTW.getSettings().setJavaScriptEnabled(true);
        wvTW.getSettings().setDomStorageEnabled(true);
        wvTW.loadUrl("file:///android_asset/twitter.html");

        return v;
    }

}
