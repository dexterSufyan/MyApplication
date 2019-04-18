package com.example.myapplication;

import android.content.Intent;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    EditText Et_Email;
    EditText Et_Pass;
    Button login;
    Button signup;
    FirebaseAuth auth;
    FirebaseUser user;
    Button google;
    GoogleSignInClient googleSignInClient;
    GoogleSignInOptions signInOptions;
    private int LOGIN = 1;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        init();
        signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("856761663116-e5hueaaj0qgrbb6ho7a4sh0nferde7n9.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, LOGIN);

            }
        });






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

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){

        if (requestCode == LOGIN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            if (task.isSuccessful()) {

                GoogleSignInAccount acc = task.getResult();

                AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
                auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            String name = user.getDisplayName();
                            String Uid = user.getUid();
                            String email = user.getEmail();
                            String address=user.getEmail();
                            String image = user.getPhotoUrl().toString();
                            savedata(name,"",address,email,"",Uid,image);

                            Toast.makeText(Login.this, "login", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
            else {
                Toast.makeText(this, task.getException().toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savedata(String name, String pass, String email,String Address, String contact, String uid, String image) {
        Toast.makeText(this,uid, Toast.LENGTH_SHORT).show();
        User user = new User(image,name,email,Address,contact);
        databaseReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText(Login.this, "added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login.this,onlinemain.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoginUser(String name, String pass) {

        auth.signInWithEmailAndPassword(name, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, onlinemain.class));
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
        google=findViewById(R.id.btn_google);
        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("user");

    }

}

