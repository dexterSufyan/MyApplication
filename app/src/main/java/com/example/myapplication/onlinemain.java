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

public class onlinemain extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinemain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

                } else if (item[i].equals("Add Player")) {

                    startActivity(new Intent(onlinemain.this,Addplayer.class));
                } else if (item[i].equals("Add Team")) {

                    startActivity(new Intent(onlinemain.this,Addteam.class));
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
                   System.exit(0);
                    }
                }).show();


                }

    }


