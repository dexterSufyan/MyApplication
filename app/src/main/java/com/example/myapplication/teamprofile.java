package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class teamprofile extends AppCompatActivity {
ImageView contact;
Button Sendrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamprofile);

    init();
    Sendrequest.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(teamprofile.this,booking.class));
        }
    });
    contact.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent call= new Intent(getIntent().ACTION_CALL);
            call.getData();
            startActivity(call);

        }
    });
    }

    private void init() {
        contact=findViewById(R.id.Contact);
        Sendrequest=findViewById(R.id.btn_request);

    }
}
