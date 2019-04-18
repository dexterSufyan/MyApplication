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

public class bridgeteam extends RecyclerView.Adapter<teamViewHolder> {
    ArrayList<captain> getteamlists;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference teamRef;


    public bridgeteam(ArrayList<captain> gethomelists, Context context) {
        this.getteamlists = gethomelists;
        this.context = context;
    }

    @NonNull
    @Override
    public teamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.teamviewlist, viewGroup, false);
        teamViewHolder teamview = new teamViewHolder(view);



        return teamview;
    }

    @Override
    public void onBindViewHolder(@NonNull final teamViewHolder homeViewHolder, int i) {
        final captain team = getteamlists.get(i);
        //homeViewHolder.Teampic.setImageResource(team.getTeampic());
        homeViewHolder.Teamname.setText(team.getName());

        homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(homeViewHolder.itemView.getContext(),userprofile.class);
                homeViewHolder.itemView.getContext().startActivity(intent);
         teamRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 getteamviewlist team =dataSnapshot.getValue(getteamviewlist.class);
                 homeViewHolder.Teamname.setText(team.getTeamname());
                 homeViewHolder.TeamAdd.setText(team.getAddress());
                 Glide.with(context).load(team.teampic).into(homeViewHolder.Teampic);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });



            }
        });
    }

    @Override
    public int getItemCount() {
        return getteamlists.size();
    }

    private void init() {
        firebaseDatabase= FirebaseDatabase.getInstance();
        teamRef=firebaseDatabase.getReference().child("Team");
    }
}
