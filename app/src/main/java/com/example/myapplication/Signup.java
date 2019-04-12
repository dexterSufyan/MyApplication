package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
EditText Et_username;
EditText Et_pass;
EditText Et_email;
EditText Et_address;
EditText Et_contact;
FirebaseAuth auth;
FirebaseUser user;
Button btn_Signup;
FirebaseDatabase database;
DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
       init();

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Et_email.getText().toString();
                String pass = Et_pass.getText().toString();
                String name = Et_username.getText().toString();
                String Address = Et_address.getText().toString();
                String Contact = Et_contact.getText().toString();
                signupUser(name,email,pass,Address,Contact);
                Et_email.setText("");
                Et_pass.setText("");
                Et_username.setText("");
                Et_address.setText("");
                Et_contact.setText("");
            }
        });
    }
    private void signupUser(final String name, final String email, String pass, final String Address, final String Contact) {

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String id=user.getUid();
                    saveData(id,email,name,Address,Contact);
                    Toast.makeText(Signup.this, "signup", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Signup.this, onlinemain.class));
                } else {
                    Toast.makeText(Signup.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void saveData(String id, String email, String name, String address, String contact) {
        User user=new User(id,name,email,address,contact);
        reference.child(id).setValue(user);
    }


    private void init() {
        Et_username=findViewById(R.id.Et_sign_username);
        Et_pass=findViewById(R.id.Et_sign_pass);
        Et_email=findViewById(R.id.Et_sign_email);
        Et_address=findViewById(R.id.Et_sign_address);
        Et_contact=findViewById(R.id.Et_sign_contact);
        btn_Signup=findViewById(R.id.btn_signUp);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");
        user=auth.getCurrentUser();
    }

    }
