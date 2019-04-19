package com.example.myapplication;

import android.content.Context;
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

public class bridgeground extends RecyclerView.Adapter<groundViewholder> {
ArrayList<getgroundviewlist> getgroundviewlists;
Context groundcontext;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference GroundRef;

    public bridgeground(ArrayList<getgroundviewlist> getgroundviewlists, Context ground) {
        this.getgroundviewlists = getgroundviewlists;
        this.groundcontext = ground;
    }

    @NonNull
    @Override
    public groundViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(groundcontext);
        View view = inflater.inflate(R.layout.grounviewlist, viewGroup, false);
        groundViewholder groundview = new groundViewholder(view);
        return groundview;
    }

    @Override
    public void onBindViewHolder(@NonNull final groundViewholder groundViewholder, int i) {
        getgroundviewlist ground = getgroundviewlists.get(i);
        //groundViewholder.groundpic.setImageResource(ground.getGroundpic());
       GroundRef.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               getgroundviewlist ground = dataSnapshot.getValue(getgroundviewlist.class);

               groundViewholder.groundname.setText(ground.groundname);
               groundViewholder.groundAdd.setText(ground.getAddress());
               Glide.with(groundcontext).load(ground.groundpic).into(groundViewholder.groundpic);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
    }

    @Override
    public int getItemCount() {
        return getgroundviewlists.size();
    }
    private void init() {
        firebaseDatabase= FirebaseDatabase.getInstance();
        GroundRef=firebaseDatabase.getReference("Ground");
    }
}
