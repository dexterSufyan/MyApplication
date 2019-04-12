package com.example.myapplication;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class newgame extends AppCompatActivity {
EditText Et_team1;
EditText Et_team2;
EditText Et_overs;
EditText Et_venue;
boolean check;
Button starttoss;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference historyRef;
    DatabaseReference matchRef;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        init();

        starttoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String team1 = Et_team1.getText().toString();
                String team2 = Et_team2.getText().toString();
                String overs = Et_overs.getText().toString();
                String venue = Et_venue.getText().toString();
                String HistoryId=historyRef.push().getKey();
                String matchinfoId=matchRef.push().getKey();
                check = false;
                if (team1.isEmpty()) {
                    Et_team1.setError("Required Field");
                    check = true;
                }
                if (team2.isEmpty()) {
                    Et_team2.setError("Required Field");
                    check = true;
                }
                if (overs.isEmpty()) {
                    Et_overs.setError("Required Field");
                    check = true;
                }
                if (venue.isEmpty()) {
                    Et_venue.setError("Required Field");
                    check = true;
                }
                if (!check) {
                    Intent playgame = new Intent(newgame.this, Playgame.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("team1", team1);
                    bundle.putString("team2", team2);
                    bundle.putString("overs", overs);
                    bundle.putString("venue", venue);

                    playgame.putExtras(bundle);
                    startActivity(playgame);
                }
               uploaddata(team1,team2,venue,overs,HistoryId,matchinfoId);
            }

        });
    }

    private void uploaddata(String team1, String team2, String venue, String overs, String historyId, String matchinfoId) {
        matchinfo matchinfos= new matchinfo(team1,team2,overs,venue,historyId,matchinfoId);

        historyRef.child(historyId).setValue(matchinfos);
        matchRef.child(matchinfoId).child("MatchId").setValue(matchinfos);


    }


    private void init() {
        Et_team1=findViewById(R.id.et_team1);
        Et_team2=findViewById(R.id.et_team2);
        Et_overs=findViewById(R.id.et_over);
        Et_venue=findViewById(R.id.et_venue);
        starttoss=findViewById(R.id.btn_toss);
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        historyRef = firebaseDatabase.getReference("history");
        matchRef=historyRef.child("matchinfo");
    }
}


