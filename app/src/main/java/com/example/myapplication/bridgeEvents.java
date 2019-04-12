package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class bridgeEvents extends RecyclerView.Adapter<eventViewHolder> {
    ArrayList<geteventviewlist> geteventviewlists;
    Context contextevent;

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
    public void onBindViewHolder(@NonNull eventViewHolder eventViewHolder, int i) {
        final geteventviewlist event = geteventviewlists.get(i);
       // eventViewHolder.Eventpic.setImageResource(event.getEventpic());
        eventViewHolder.Eventname.setText(event.getEvent_name());
        eventViewHolder.Eventurl.setText(event.getEvent_url());
    }

    @Override
    public int getItemCount() {
        return geteventviewlists.size();
    }
}
