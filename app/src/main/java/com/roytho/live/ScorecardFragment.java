package com.roytho.live;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScorecardFragment extends Fragment {

    WebView wvInn1, wvInn2, wvInn3, wvInn4, wvODI1, wvODI2;
    SwipeRefreshLayout swipeRefreshLayout;

    public ScorecardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_scorecard, container, false);

        swipeRefreshLayout = v.findViewById(R.id.scorecard_refresh);
        wvInn1 = v.findViewById(R.id.wvInn1);
        wvInn2 = v.findViewById(R.id.wvInn2);
        wvInn3 = v.findViewById(R.id.wvInn3);
        wvInn4 = v.findViewById(R.id.wvInn4);
        wvODI1 = v.findViewById(R.id.wvOdi1);
        wvODI2 = v.findViewById(R.id.wvOdi2);

        TabHost tabs=(TabHost)v.findViewById(R.id.scorecard); //Id of tab host
        tabs.setHorizontalScrollBarEnabled(true);

        tabs.setup();

        TabHost.TabSpec spec=tabs.newTabSpec("INN 1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("INN 1");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("INN 2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("INN 2");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("INN 3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("INN 3");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("INN 4");
        spec.setContent(R.id.tab4);
        spec.setIndicator("INN 4");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("ODI Day 1");
        spec.setContent(R.id.tab5);
        spec.setIndicator("ODI Day 1");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("ODI Day 2");
        spec.setContent(R.id.tab6);
        spec.setIndicator("ODI Day 2");
        tabs.addTab(spec);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                wvInn1.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=1");
                wvInn2.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=2");
                wvInn3.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=3");
                wvInn4.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=4");
                wvODI1.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=odi1");
                wvODI2.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=odi2");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        wvInn1.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=1");
        wvInn2.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=2");
        wvInn3.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=3");
        wvInn4.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=4");
        wvODI1.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=odi1");
        wvODI2.loadUrl("https://stcicts.org/roytho/scorecard.php?inning=odi2");

        return v;
    }

}
