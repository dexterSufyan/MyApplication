package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class playerViewHolder extends RecyclerView.ViewHolder {
    ImageView dp;
    TextView playername,teamname,playertype;
    public playerViewHolder(@NonNull View itemView) {
        super(itemView);

        dp=itemView.findViewById(R.id.imgview);
        playername=itemView.findViewById(R.id.tv_playername);
        teamname=itemView.findViewById(R.id.tv_Teamname);
        playertype=itemView.findViewById(R.id.tv_playetype);

    }
}
