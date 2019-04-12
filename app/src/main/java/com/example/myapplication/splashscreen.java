package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.myapplication.R.anim.scale;

public class splashscreen extends AppCompatActivity {
    Snackbar snackbar;
    RelativeLayout container;
    ImageView logo;
    TextView tagline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        container=findViewById(R.id.splash_container);
        logo = findViewById(R.id.logo);


        //Animation fade = AnimationUtils.loadAnimation(this, R.anim.fade);
       // logo.startAnimation(fade);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AnimationDrawable animation=new AnimationDrawable();
                //startActivity( new Intent(splashscreen.this,MainActivity.class));
            }


        }; handler.postDelayed(runnable,4000);

        testConnection();
    }

    private void testConnection() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());

        if (info != null && info.isConnected()) {

            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                showSnackBar(1);
            } else {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
                showSnackBar(2);
            }

        }
        else {
            showSnackBar(0);

        }
    }

    private void showSnackBar(int check) {

        if (check == 1) {

            snackbar = Snackbar.make(container, "Connected To Wifi", Snackbar.LENGTH_LONG);
            snackbar.show();
            startActivity(new Intent(splashscreen.this,Login.class));

        }
        else if (check == 2) {

            snackbar = Snackbar.make(container, "Connected To Mobile Data", Snackbar.LENGTH_LONG);
            snackbar.show();
            startActivity(new Intent(splashscreen.this,Login.class));
        }
        else {
            snackbar = Snackbar.make(container, "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
            startActivity(new Intent(splashscreen.this,MainActivity.class));
            snackbar.setAction("RE-TRY", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testConnection();
                }
            });
            snackbar.setActionTextColor(Color.BLUE);
            View view = snackbar.getView();
            view.setBackgroundColor(Color.RED);
            view.setMinimumHeight(20);
            TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

    }


}
