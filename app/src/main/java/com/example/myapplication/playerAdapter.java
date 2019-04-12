package com.example.myapplication;

import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class playerAdapter extends RecyclerView.Adapter<playerViewHolder> {
    ArrayList<Player> players;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;

    public playerAdapter(ArrayList<Player> players, Context context) {
        this.players = players;
        this.context = context;
    }

    @NonNull
    @Override
    public playerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.playerview,viewGroup,false);
        playerViewHolder playerView= new playerViewHolder(view);
        return playerView;
    }


    @Override
    public void onBindViewHolder(@NonNull playerViewHolder playerViewHolder, int i) {
        final Player player=players.get(i);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("books").child(player.getPlayerId());

    }

            @Override
    public int getItemCount() {
        return players.size();
    }
}
