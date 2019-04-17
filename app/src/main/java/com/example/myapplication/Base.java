package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class Base extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;
    public static FrameLayout frameLayout;
    TextView username, email;
    FirebaseAuth auth;
    FirebaseUser User;
    FirebaseDatabase database;
    DatabaseReference datbasereference;
    FirebaseStorage storage;
    StorageReference refrence;
    CircleImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);



        NavigationView navigationView=findViewById(R.id.navigation_view);
        View view= navigationView.getHeaderView(0);
        username=view.findViewById(R.id.header_username);
        email=view.findViewById(R.id.header_email);
        imageView=view.findViewById(R.id.header_image);



        init();


        setUpDrawer();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_container, new Home());
        transaction.commit();

        navigationView.setCheckedItem(R.id.home);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(Base.this,onlinemain.class));
                        break;

                    case R.id.Notification:
                        showFragment(new Notification());
                        break;

                    case R.id.Profile:
                        showFragment(new profile());
                        break;
                }

                drawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });


        datbasereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 User user = dataSnapshot.getValue(User.class);

                Toast.makeText(Base.this, user.getName(), Toast.LENGTH_SHORT).show();
                username.setText(user.getName());
                email.setText(user.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void showFragment(Fragment fragment) {

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_container, fragment);
        transaction.commit();
    }

    private void setUpDrawer() {

        setSupportActionBar(toolbar);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation, R.string.close_navigation);
        fragmentManager = getSupportFragmentManager();
        frameLayout = findViewById(R.id.frag_container);
        imageView = findViewById(R.id.header_image);
        username = findViewById(R.id.header_username);
        email = findViewById(R.id.header_email);

        auth = FirebaseAuth.getInstance();
        User = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        datbasereference = database.getReference("users").child(User.getUid());
        storage = FirebaseStorage.getInstance();

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else{

            super.onBackPressed();
        }
    }


}
