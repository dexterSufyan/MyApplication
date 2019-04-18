package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class adapterevent extends RecyclerView.Adapter<bookeventviewholder> {
    ArrayList<geteventviewlist> geteventlists;
    Context event;

    public adapterevent(ArrayList<geteventviewlist> geteventlists, Context event) {
        this.geteventlists = geteventlists;
        this.event = event;
    }

    @NonNull
    @Override
    public bookeventviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(event);
        View view = inflater.inflate(R.layout.bookeventview, viewGroup, false);
        bookeventviewholder bookeventviewholder = new bookeventviewholder(view);

        return bookeventviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull bookeventviewholder bookeventviewholder, int i) {
        final geteventviewlist geteventlist = geteventlists.get(i);
        bookeventviewholder.date.setText(geteventlist.getEvent_name());
        bookeventviewholder.time.setText(geteventlist.getEvent_url());
        bookeventviewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.startActivity(new Intent(event, EventDetails.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return geteventlists.size();
    }
}
