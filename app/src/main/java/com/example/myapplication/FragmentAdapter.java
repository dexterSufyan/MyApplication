package com.example.myapplication;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    ArrayList<Fragment> fragments;
    public FragmentAdapter(FragmentManager fm,ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "newsFragment";
            case 1:
                return "teamview";
            case 2:
                return "Groundview";
            case 3:
                return "EventView";

            default:
                return "";


        }

    }
}
