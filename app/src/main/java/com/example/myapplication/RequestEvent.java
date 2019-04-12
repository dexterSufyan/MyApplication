package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RequestEvent extends AppCompatActivity {
Button Confirm,cancel;
EditText teamname,date,time,venue;
    DatabaseReference EventRequestRef;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);
   Init();
   cancel.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           startActivity(new Intent(RequestEvent.this,onlinemain.class));
       }
   });
   Confirm.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           String ER_teamname = teamname.getText().toString();
           String ER_time = time.getText().toString();
           String ER_date = date.getText().toString();
           String ER_venue = venue.getText().toString();
           String EventRequestId=EventRequestRef.push().getKey();

       }
   });
    }

    private void Init() {
    Confirm=findViewById(R.id.btn_confirm);
    cancel=findViewById(R.id.btn_cancel);
    teamname=findViewById(R.id.Et_ED_teamname);
    date=findViewById(R.id.Et_ED_date);
    time=findViewById(R.id.Et_ED_time);
    venue=findViewById(R.id.Et_ED_venue);
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        EventRequestRef = firebaseDatabase.getReference("Eventrequest");
    }
}
