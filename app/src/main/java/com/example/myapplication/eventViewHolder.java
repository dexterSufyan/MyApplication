package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class eventViewHolder extends RecyclerView.ViewHolder {

    ImageView Eventpic;
    TextView Eventname;
    TextView Eventurl;
    public eventViewHolder(@NonNull View itemView) {
        super(itemView);
        Eventpic=itemView.findViewById(R.id.event_profile_img);
        Eventname=itemView.findViewById(R.id.text_Event_name);
        Eventurl=itemView.findViewById(R.id.text_event_url);
    }
}
