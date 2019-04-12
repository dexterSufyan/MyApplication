package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class bookeventviewholder extends RecyclerView.ViewHolder {
    TextView time,date;
    public bookeventviewholder(@NonNull View itemView) {
        super(itemView);
        time=itemView.findViewById(R.id.event_time);
        date=itemView.findViewById(R.id.event_date);

    }
}
