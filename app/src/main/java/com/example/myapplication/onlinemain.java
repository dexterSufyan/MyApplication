package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class onlinemain extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    DatabaseReference adminREf;
    String uid;
    Button btn;
    FirebaseAuth auth;
    String adminId;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinemain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth=FirebaseAuth.getInstance();
        uid=auth.getCurrentUser().getUid();
        database=FirebaseDatabase.getInstance();
        btn=findViewById(R.id.add);

        bottomNavigationView = findViewById(R.id.btm_nv);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        selector(new Home());
                        break;
                    case R.id.Profile:
                        selector(new profile());
                        break;
                    case R.id.notification:
                        selector(new Notification());
                        break;
                    case R.id.Start:
                       // selector(new Start());
                        showAlertDialog();
                        break;
                    case R.id.Events:
                        selector(new Event());
                        break;
                }
                return true;
            }


        });

        adminREf=database.getReference().child("admin");
        adminREf.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminId=dataSnapshot.child("adminId").getValue(String.class);

                if(uid.equals(adminId)){
                    btn.setVisibility(View.VISIBLE);
                    btn.setClickable(true);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final String[] item = {"Add Player","Add Team","Add Event","Add Ground","cancel"};
                            AlertDialog.Builder builder = new AlertDialog.Builder(onlinemain.this);
                            View view  = getLayoutInflater().inflate(R.layout.alertboox,null);
                            builder.setTitle("Select Options");
                            builder.setItems(item, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    if (item[i].equals("Add Player")) {
                                        startActivity(new Intent(onlinemain.this,Addplayer.class));

                                    }
                                    else if(item[i].equals("Add Event"))
                                    {
                                        startActivity(new Intent(onlinemain.this,AddEvent.class));
                                    }
                                    else if(item[i].equals("Add Team"))
                                    {
                                        startActivity(new Intent(onlinemain.this,Addteam.class));
                                    }
                                    else if(item[i].equals("Add Ground"))
                                    {
                                        startActivity(new Intent(onlinemain.this,AddGround.class));
                                    }
                                    else if (item[i].equals("Cancel")) {
                                        dialog.dismiss();
                                    }
                                }
                            });
                            builder.show();

                        }
                    });
                }
                else {
                    btn.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }

    private void showAlertDialog() {
         final String[] item = {"New Game","Add Player","Add Team","Add Event","cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view  = getLayoutInflater().inflate(R.layout.alertboox,null);
        builder.setTitle("Select Options");
        builder.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (item[i].equals("New Game")) {
                    startActivity(new Intent(onlinemain.this,newgame.class));

                }
                else if(item[i].equals("Add Event"))
                {
                    startActivity(new Intent(onlinemain.this,AddEvent.class));
                }
                else if (item[i].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    private void selector(Fragment fragment) {

            transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();

    }
    public void onBackPressed() {
       AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Really Exit?");
               builder.setMessage("Are you sure you want to exit?");
               builder.setCancelable(false);
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();


                }

    }


