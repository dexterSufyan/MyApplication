package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
public class Event extends Fragment {
    ArrayList<geteventviewlist> eventlist;
    adapterevent event;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference eventRef;
    RecyclerView listevent;

    public Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        init();
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        eventlist = new ArrayList<>();
//        eventlist.add(new geteventlist("12:30","2/3/2017"));
//        eventlist.add(new geteventlist("12:30","2/3/2017"));
//        eventlist.add(new geteventlist("12:30","2/3/2017"));
//        eventlist.add(new geteventlist("12:30","2/3/2017"));
        event= new adapterevent(eventlist,getContext());
        listevent=view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listevent.setLayoutManager(linearLayoutManager);
        listevent.setAdapter(event);


        eventRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                geteventviewlist events = dataSnapshot.getValue(geteventviewlist.class);
                eventlist.add(events);
                event.notifyDataSetChanged();
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

    private void init() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        eventRef = firebaseDatabase.getReference("Events");
    }

}
