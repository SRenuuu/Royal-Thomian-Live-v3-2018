package com.roytho.live.home;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.roytho.live.R;
import com.roytho.live.home.commentary.Comment;
import com.roytho.live.home.commentary.CommentsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentaryFragment extends Fragment {

    FirebaseDatabase database;
    ProgressDialog pd;
    private RecyclerView rv;
    private CommentsAdapter cadapter;
    public List<Comment> data;

    public CommentaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_commentary, container, false);

        rv = v.findViewById(R.id.comments_list);
        data = new ArrayList<>();

        database = FirebaseDatabase.getInstance();

        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading commentary");
        pd.show();

        return v;
    }

    @Override
    public void onStart() {

        Query commentaryRef = database.getReference("commentary").limitToLast(5).orderByKey();
        commentaryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                getAllComments(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    private void getAllComments(DataSnapshot dataSnapshot) {
        pd.dismiss();
        for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){

            String comment = singleSnapshot.child("text").getValue().toString();
            String state = singleSnapshot.child("state").getValue().toString();

            Comment comm = new Comment();
            comm.setComment(comment);
            comm.setState(state);

            data.add(comm);
        }

        Collections.reverse(data);
        cadapter = new CommentsAdapter(getActivity(), data);
        rv.setAdapter(cadapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
