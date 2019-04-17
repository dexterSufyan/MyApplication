package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class bridgeground extends RecyclerView.Adapter<groundViewholder> {
ArrayList<getgroundviewlist> getgroundviewlists;
Context ground;

    public bridgeground(ArrayList<getgroundviewlist> getgroundviewlists, Context ground) {
        this.getgroundviewlists = getgroundviewlists;
        this.ground = ground;
    }

    @NonNull
    @Override
    public groundViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ground);
        View view = inflater.inflate(R.layout.grounviewlist, viewGroup, false);
        groundViewholder groundview = new groundViewholder(view);
        return groundview;
    }

    @Override
    public void onBindViewHolder(@NonNull groundViewholder groundViewholder, int i) {
        getgroundviewlist ground = getgroundviewlists.get(i);
        //groundViewholder.groundpic.setImageResource(ground.getGroundpic());
        groundViewholder.groundname.setText(ground.getGroundname());
        groundViewholder.groundAdd.setText(ground.getAddress());
    }

    @Override
    public int getItemCount() {
        return getgroundviewlists.size();
    }
}
