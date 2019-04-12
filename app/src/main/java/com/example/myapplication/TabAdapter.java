package com.example.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> Tabfragment;

    public TabAdapter(FragmentManager fm, ArrayList<Fragment> tabfragment) {
        super(fm);
        Tabfragment = tabfragment;
    }


    @Override
    public Fragment getItem(int i) {
        return Tabfragment.get(i);
    }

    @Override
    public int getCount() {
        return Tabfragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "RecordScore";
            case 1:
                return "Scorecard";

            default:
                return "";


        }


    }
}
