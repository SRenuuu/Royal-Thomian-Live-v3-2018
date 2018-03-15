package com.roytho.live;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {


    WebView wvFB, wvTW;

    public TeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_teams, container, false);

        TabHost tabs=v.findViewById(R.id.teamtabs); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("STC");
        spec.setContent(R.id.tab1);
        spec.setIndicator("STC");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("RC");
        spec.setContent(R.id.tab2);
        spec.setIndicator("RC");
        tabs.addTab(spec);

        wvFB = v.findViewById(R.id.wvTSTC);
        wvFB.loadUrl("https://stcicts.org/roytho/team/stc.php");

        wvTW = v.findViewById(R.id.wvTRC);
        wvTW.loadUrl("https://stcicts.org/roytho/team/rc.php");

        return v;
    }

}
