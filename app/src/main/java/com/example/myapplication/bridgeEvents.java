package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class bridgeEvents extends RecyclerView.Adapter<eventViewHolder> {
    ArrayList<geteventviewlist> geteventviewlists;
    Context contextevent;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference eventRef;
    public bridgeEvents(ArrayList<geteventviewlist> geteventviewlists, Context contextevent) {
        this.geteventviewlists = geteventviewlists;
        this.contextevent = contextevent;
    }

    @NonNull
    @Override
    public eventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(contextevent);
        View view = inflater.inflate(R.layout.eventviewlist, viewGroup, false);
        eventViewHolder eventViewHolder= new eventViewHolder(view);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final eventViewHolder eventViewHolder, final int i) {
        final geteventviewlist event = geteventviewlists.get(i);
       // eventViewHolder.Eventpic.setImageResource(event.getEventpic());

        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                geteventviewlist event= dataSnapshot.getValue(geteventviewlist.class);
                eventViewHolder.Eventname.setText(event.getEvent_name());
                eventViewHolder.Eventurl.setText(event.getEvent_url());

                Glide.with(contextevent).load(event.getEventpic()).into(eventViewHolder.Eventpic);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return geteventviewlists.size();
    }

    private void init() {
        firebaseDatabase= FirebaseDatabase.getInstance();
        eventRef=firebaseDatabase.getReference("Events");
    }

}


