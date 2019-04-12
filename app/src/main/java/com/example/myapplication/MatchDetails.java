package com.example.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MatchDetails extends AppCompatActivity {
    ViewPager pager;
    TabAdapter adapter;
    ArrayList<Fragment> list;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);
        init();
      //  list.add(new RecordScore());
        list.add(new Scoreboard());
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setIcon(android.R.drawable.btn_star);
        tabs.getTabAt(1).setIcon(android.R.drawable.ic_menu_call);

    }

    private void init() {
        pager=findViewById(R.id.view_pager);
        list=new ArrayList<>();
        adapter=new TabAdapter(getSupportFragmentManager(),list);
        tabs=findViewById(R.id.tabs);

    }

}
