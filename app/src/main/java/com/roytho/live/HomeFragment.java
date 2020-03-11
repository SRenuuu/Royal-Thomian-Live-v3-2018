package com.roytho.live;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.roytho.live.home.CommentaryFragment;
import com.roytho.live.home.LiveScoreFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    BottomNavigationView bottomNavigationMenu;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);

        bottomNavigationMenu = v.findViewById(R.id.home_navigation);
        bottomNavigationMenu.setSelectedItemId(R.id.navigation_livescores);
        setHomeView(new LiveScoreFragment());

        bottomNavigationMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch(item.getItemId()){
                    case R.id.navigation_commentary:
                        fragment = new CommentaryFragment();
                        setHomeView(fragment);
                        return true;
                    case R.id.navigation_livescores:
                        fragment = new LiveScoreFragment();
                        setHomeView(fragment);
                        return true;
                }
                return false;
            }
        });
        return  v;
    }

    private void setHomeView(Fragment fragment){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.homecontainer, fragment).commit();
    }

}
