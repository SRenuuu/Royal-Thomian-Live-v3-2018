package com.roytho.live.home;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roytho.live.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LiveScoreFragment extends Fragment {

    FirebaseDatabase database;
    TextView tvBat1name, tvBat2name, tvBat1score, tvBat2score, tvBowler, tvBOvers, tvBMaiden, tvRuns, tvWickets;

    public LiveScoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_live_score, container, false);

        tvBat1name = v.findViewById(R.id.txtBats1);
        tvBat2name = v.findViewById(R.id.txtBats2);
        tvBat1score = v.findViewById(R.id.txtBat1Score);
        tvBat2score = v.findViewById(R.id.txtBat2Score);

        tvBowler = v.findViewById(R.id.txtBowler);
        tvBOvers = v.findViewById(R.id.txtBOvers);
        tvBMaiden = v.findViewById(R.id.txtMaiden);
        tvRuns = v.findViewById(R.id.txtRuns);
        tvWickets = v.findViewById(R.id.txtBWickets);

        database = FirebaseDatabase.getInstance();

        return v;
    }

    @Override
    public void onStart() {


        DatabaseReference mainScoreRef = database.getReference("mainscore");
        mainScoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String bat1_name = dataSnapshot.child("bat1_name").getValue(String.class);
                String bat2_name = dataSnapshot.child("bat2_name").getValue(String.class);
                String bat1_score = dataSnapshot.child("bat1_score").getValue(String.class);
                String bat2_score = dataSnapshot.child("bat2_score").getValue(String.class);

                String bowler = dataSnapshot.child("bowl/name").getValue(String.class);
                String bowler_o = dataSnapshot.child("bowl/o").getValue(String.class);
                String bowler_m = dataSnapshot.child("bowl/m").getValue(String.class);
                String bowler_r = dataSnapshot.child("bowl/r").getValue(String.class);
                String bowler_w = dataSnapshot.child("bowl/w").getValue(String.class);

                tvBat1name.setText(bat1_name);
                tvBat2name.setText(bat2_name);
                tvBat1score.setText(bat1_score);
                tvBat2score.setText(bat2_score);

                tvBowler.setText(bowler);
                tvBOvers.setText(bowler_o);
                tvBMaiden.setText(bowler_m);
                tvRuns.setText(bowler_r);
                tvWickets.setText(bowler_w);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onStart();
    }
}
