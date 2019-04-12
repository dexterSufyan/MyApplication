package com.example.myapplication;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Start extends Fragment {
Button addteam,addplayer,Newgame,addevent;

    public Start() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addteam=container.findViewById(R.id.addteam);
        addplayer=container.findViewById(R.id.addplayer);
        Newgame=container.findViewById(R.id.newgame);
        addevent=container.findViewById(R.id.AddEvent);
        // Inflate the layout for this fragment
      addteam.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getContext(),Addteam.class));
          }
      });
      addplayer.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getContext(),Addplayer.class));
          }
      });
      Newgame.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getContext(),newgame.class));
          }
      });
      addevent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getContext(),AddEvent.class));

          }
      });
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        return view;
    }

}
