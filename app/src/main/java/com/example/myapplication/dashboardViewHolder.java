package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class dashboardViewHolder extends RecyclerView.ViewHolder {
    ImageView img_title;
    TextView text_title;

    public dashboardViewHolder(@NonNull View itemView) {
        super(itemView);
        img_title = itemView.findViewById(R.id.img);
        text_title = itemView.findViewById(R.id.text_android);
    }

}
