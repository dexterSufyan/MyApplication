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

public class Login extends AppCompatActivity {
    EditText Et_Email;
    EditText Et_Pass;
    Button login;
    Button signup;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        init();

       /* if (user != null) {
            startActivity(new Intent(this, onlinemain.class));
        }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Et_Email.getText().toString();
                String pass = Et_Pass.getText().toString();
                if (email.isEmpty()) {
                    Et_Email.setError("Email required");
                } else if (pass.isEmpty()) {
                    Et_Pass.setError("Password required");
                }

                LoginUser(email, pass);

                Et_Email.setText("");
                Et_Pass.setText("");

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });

    }

    private void LoginUser(String name, String pass) {

        auth.signInWithEmailAndPassword(name, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Home.class));
                } else {
                    Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void init() {

        Et_Email = findViewById(R.id.et_username);
        Et_Pass = findViewById(R.id.et_pass);
        login = findViewById(R.id.btn_Login_user);
        signup = findViewById(R.id.btn_User_signup);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

    }

}

