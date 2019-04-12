package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class bridgeteam extends RecyclerView.Adapter<teamViewHolder> {
    ArrayList<captain> getteamlists;
    Context team;

    public bridgeteam(ArrayList<captain> gethomelists, Context context) {
        this.getteamlists = gethomelists;
        team = context;
    }

    @NonNull
    @Override
    public teamViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(team);
        View view = inflater.inflate(R.layout.teamviewlist, viewGroup, false);
        teamViewHolder teamview = new teamViewHolder(view);



        return teamview;
    }

    @Override
    public void onBindViewHolder(@NonNull final teamViewHolder homeViewHolder, int i) {
        final captain team = getteamlists.get(i);
        //homeViewHolder.Teampic.setImageResource(team.getTeampic());
        homeViewHolder.Teamname.setText(team.getName());

        homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent=new Intent(homeViewHolder.itemView.getContext(),userprofile.class);
                homeViewHolder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return getteamlists.size();
    }
}
