/* APP DEVELOPED BY SURESH MICHAEL PEIRIS (tsmpmail@gmail.com) */

package com.roytho.live;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase database;
    TextView tvRCScore, tvSTCScore, tvRCOvers, tvSTCOvers, tvBatTeam, tvInning;
    RelativeLayout mainscorearea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawercontainer, new HomeFragment()).commit();
        navigationView.setCheckedItem(0);

        mainscorearea = findViewById(R.id.mainscorearea);

        tvRCScore = findViewById(R.id.rcscore);
        tvSTCScore = findViewById(R.id.stcscore);
        tvRCOvers = findViewById(R.id.rcovers);
        tvSTCOvers = findViewById(R.id.stcovers);
        tvBatTeam = findViewById(R.id.tvBattingTeam);
        tvInning = findViewById(R.id.tvInning);
    }

    @Override
    protected void onStart() {

        DatabaseReference mainScoreRef = database.getReference("mainscore");

        mainScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String stc_total = dataSnapshot.child("stc_total").getValue().toString();
                String stc_overs = dataSnapshot.child("stc_overs").getValue().toString();
                String stc_wickets = dataSnapshot.child("stc_wickets").getValue().toString();

                String rc_total = dataSnapshot.child("rc_total").getValue().toString();
                String rc_overs = dataSnapshot.child("rc_overs").getValue().toString();
                String rc_wickets = dataSnapshot.child("rc_wickets").getValue().toString();

                String curr_batting = dataSnapshot.child("curr_batting").getValue().toString();
                String curr_inning = dataSnapshot.child("curr_inning").getValue().toString();
                String curr_live = dataSnapshot.child("curr_live").getValue().toString();

                switch(curr_inning){
                    case "1":
                        tvInning.setText("Inning 1");
                        break;
                    case "2":
                        tvInning.setText("Inning 2");
                        break;
                    case "odi1":
                        tvInning.setText("Mustangs Trophy - Day 01");
                        break;
                    case "odi2":
                        tvInning.setText("Mustangs Trophy - Day 02");
                        break;
                    default:
                        tvInning.setText("139th Battle of the Blues");
                    break;
                }

                double stcRR = round(Float.parseFloat(stc_total) / Float.parseFloat(stc_overs), 1);
                double rcRR = round(Float.parseFloat(rc_total) / Float.parseFloat(rc_overs), 1);

                tvSTCScore.setText(stc_total+"/"+stc_wickets);
                tvSTCOvers.setText(stc_overs+" // RR:"+stcRR);

                tvRCScore.setText(rc_total+"/"+rc_wickets);
                tvRCOvers.setText(rc_overs+" // RR:"+rcRR);

                switch(curr_batting){
                    case "non":
                        mainscorearea.setBackground( getResources().getDrawable(R.drawable.stc_cover_res) );
                        tvBatTeam.setText("BATTING: PENDING");
                        break;
                    case "stc":
                        mainscorearea.setBackground( getResources().getDrawable(R.drawable.stc_cover_res) );
                        tvBatTeam.setText("BATTING: STC");
                        break;
                    case "rc":
                        mainscorearea.setBackground( getResources().getDrawable(R.drawable.rc_cover_res) );
                        tvBatTeam.setText("BATTING: RC");
                        break;
                    default:
                        mainscorearea.setBackground( getResources().getDrawable(R.drawable.stc_cover_res) );
                        tvBatTeam.setText("BATTING: PENDING");
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onStart();
    }

    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {

            fragment = new HomeFragment();

        } else if (id == R.id.nav_history) {

            fragment = new HistoryFragment();

        } else if (id == R.id.nav_scorecard) {

            fragment = new ScorecardFragment();

        } else if (id == R.id.nav_livestream) {

            Intent liveact = new Intent(getBaseContext(),LiveStream.class);
            startActivity(liveact);
            return true;

        } else if (id == R.id.nav_social){

            fragment = new SocialFragment();

        } else if (id == R.id.nav_photos){

            Intent liveact = new Intent(getBaseContext(),PhotoGallery.class);
            startActivity(liveact);
            return true;

        } else if(id == R.id.nav_teams){

            fragment = new TeamsFragment();

        } else if(id == R.id.nav_about){

            fragment = new AboutFragment();

        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawercontainer, fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
