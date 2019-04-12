package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class BridgeAdapter extends RecyclerView.Adapter<dashboardViewHolder> {
    ArrayList<getdashboard> getdashboards;
    Context dashboard;

    public BridgeAdapter(ArrayList<getdashboard> getdashboards, Context dashboard) {
        this.getdashboards = getdashboards;
        this.dashboard = dashboard;
    }

    @NonNull
    @Override
    public dashboardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(dashboard);
        View view=inflater.inflate(R.layout.dashboardview,viewGroup,false);
       dashboardViewHolder dashboardView =new dashboardViewHolder(view);

        return dashboardView;
    }

    @Override
    public void onBindViewHolder(@NonNull dashboardViewHolder dashboardViewHolder, int i) {
        final getdashboard getdashboard= getdashboards.get(i);
        dashboardViewHolder.img_title.setImageResource(getdashboard.getTitle_img());
        dashboardViewHolder.text_title.setText(getdashboard.getTitle_Text());
        dashboardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getdashboard.getTitle_Text().equals("New Game"))
                {
                    dashboard.startActivity(new Intent(dashboard,newgame.class));
                }
                else if(getdashboard.getTitle_Text().equals("Continue Game"))
                {
                    //dashboard.startActivity(new Intent(dashboard,RecordScore.class));
                }
                else if (getdashboard.getTitle_Text().equals("Add player"))
                {
                    dashboard.startActivity(new Intent(dashboard,Addplayer.class));
                }
                else if (getdashboard.getTitle_Text().equals("history"))
                {
                    dashboard.startActivity(new Intent());
                }
            else if(getdashboard.getTitle_Text().equals("Add team"))
            {
                dashboard.startActivity(new Intent(dashboard,Addteam.class));
            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return getdashboards.size();
    }
}
