package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class teamViewHolder extends RecyclerView.ViewHolder {

    CircleImageView Teampic;
    TextView Teamname;
    TextView TeamAdd;
    public teamViewHolder(@NonNull View itemView) {
        super(itemView);
        Teampic=itemView.findViewById(R.id.profile_img);
        Teamname=itemView.findViewById(R.id.text_Teamname);


    }
}
