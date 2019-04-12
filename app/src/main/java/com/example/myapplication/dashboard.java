package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {
    RecyclerView list;
    SearchView searchbar;
    ArrayList<getdashboard> getdashboards;
    BridgeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getvalue();

    }

    private void getvalue() {
        list = findViewById(R.id.list);
        searchbar=findViewById(R.id.search_bar);
        getdashboards = new ArrayList<>();
        getdashboards.add(new getdashboard(R.drawable.newgame, "New Game"));
        getdashboards.add(new getdashboard(R.drawable.continuegame, "Continue Game"));
        getdashboards.add(new getdashboard(R.drawable.addteam, "Add Team"));
        getdashboards.add(new getdashboard(R.drawable.addplayer, "Add Player"));
        getdashboards.add(new getdashboard(R.drawable.record,"History"));
        adapter=new BridgeAdapter(getdashboards,this);
        list.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
    }


}