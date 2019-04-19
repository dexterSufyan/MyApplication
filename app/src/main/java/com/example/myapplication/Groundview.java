package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Groundview extends Fragment {
    ArrayList<getgroundviewlist> groundlist;
    bridgeground ground;
    RecyclerView listground;
    FirebaseDatabase groundDatabase;
    DatabaseReference groundRef;

    public Groundview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_groundview, container, false);
        listground=view.findViewById(R.id.Ground_list);
        groundlist= new ArrayList<>();
        ground= new bridgeground(groundlist,getContext());
        listground.setAdapter(ground);
        groundDatabase=FirebaseDatabase.getInstance();
        groundRef=groundDatabase.getReference("Ground");
        groundRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                getgroundviewlist grounds =dataSnapshot.getValue(getgroundviewlist.class);
                groundlist.add(grounds);
                ground.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }

}
