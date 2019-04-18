package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    ArrayList<getteamviewlist> teamlist;
    ArrayList<captain> captainArrayList;
    bridgeteam team;
    RecyclerView listteam;
    ArrayList<getgroundviewlist> groundlist;
    bridgeground ground;
    RecyclerView listground;
    ArrayList<geteventviewlist> eventlist;
    bridgeEvents Event;
    RecyclerView listEvent;
FirebaseUser user;
FirebaseAuth auth;
    FirebaseDatabase teamDataBase;
    DatabaseReference teamRef;
    DatabaseReference refteam;
    DatabaseReference captainRef;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //list = container.findViewById(R.id.team_list);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        teamlist = new ArrayList<>();
        groundlist= new ArrayList<>();
        eventlist= new ArrayList<>();
user=auth.getCurrentUser();
auth= FirebaseAuth.getInstance();
        captainArrayList=new ArrayList<>();
        team=new bridgeteam(captainArrayList,getContext());
listteam=view.findViewById(R.id.team_list);
       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
 listteam.setAdapter(team);

        teamDataBase=FirebaseDatabase.getInstance();
        teamRef=teamDataBase.getReference("Teams");
        refteam=teamDataBase.getReference(user.getUid()).child("Teams");


        teamRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull final DataSnapshot dataSnapshot, @Nullable String s) {

                captainRef=teamRef.child(dataSnapshot.getKey()).child("Captain");
                captainRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {

                        String teamImage=dataSnapshot.child("teampic").getValue(String.class);

                        captain captainDetail=dataSnapshot.getValue(captain.class);
                        captainArrayList.add(new captain(captainDetail.getId(),captainDetail.getName(),teamImage));
                        team.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
teamRef.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        getteamviewlist teams = dataSnapshot.getValue(getteamviewlist.class);
        teamlist.add(teams);
          team.notifyDataSetChanged();

    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
//
//       // teamlist.add(new getteamviewlist(R.drawable.img_profile, "yzx","work"));
//       // teamlist.add(new getteamviewlist(R.drawable.img_profile, "abc", "xz"));
//
//
//
//        //groundlist.add(new getgroundviewlist(R.drawable.img_profile,"gymkhanna","garden east"));
//       // groundlist.add(new getgroundviewlist(R.drawable.img_profile,"ali academy","garden west"));
//
//        //eventlist.add(new geteventviewlist(R.drawable.img_profile,"tournament","url"));
//        ground= new bridgeground(groundlist,getContext());
//    //    team =new bridgeteam(teamlist,getContext());
//        Event= new bridgeEvents(eventlist,getContext());
//
//
//
//        //listground=view.findViewById(R.id.Ground_list);
//        //listEvent=view.findViewById(R.id.Event_list);
//
//
////        LinearLayoutManager groundLayout=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
////        LinearLayoutManager eventLayout=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
////      //  listteam.setLayoutManager(linearLayoutManager);
////        listground.setLayoutManager(groundLayout);
////        listEvent.setLayoutManager(eventLayout);
////
////        //listteam.setAdapter(team);
////        listground.setAdapter(ground);
////        listEvent.setAdapter(Event);

        return view;

    }

}
