package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class groundViewholder extends RecyclerView.ViewHolder {

    ImageView groundpic;
    TextView groundname;
    TextView groundAdd;
    public groundViewholder(@NonNull View itemView) {
        super(itemView);
        groundpic=itemView.findViewById(R.id.ground_profile_img);
        groundname=itemView.findViewById(R.id.text_groundname);
        groundAdd=itemView.findViewById(R.id.text_groundAddress);
    }
}
