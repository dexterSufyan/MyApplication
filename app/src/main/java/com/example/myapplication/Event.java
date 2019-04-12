package com.example.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Event extends Fragment {
    ArrayList<geteventlist> eventlist;
    adapterevent event;
    RecyclerView listevent;

    public Event() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event, container, false);
        eventlist= new ArrayList<>();
        eventlist.add(new geteventlist("12:30","2/3/2017"));
        eventlist.add(new geteventlist("12:30","2/3/2017"));
        eventlist.add(new geteventlist("12:30","2/3/2017"));
        eventlist.add(new geteventlist("12:30","2/3/2017"));
        event= new adapterevent(eventlist,getContext());
        listevent=view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        listevent.setLayoutManager(linearLayoutManager);
        listevent.setAdapter(event);
        return view;
    }

}
