package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class Addteam extends AppCompatActivity {
    EditText Address, Teamname, Contact, Email, Captainname;
    ImageView Profile;
    private static final int Pick_image = 1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference playerRef;
    DatabaseReference captainRef;
    private Uri imageUri;
    //ImageView profile;
    FirebaseStorage storage;
    StorageReference imageRef;
    Button addteam;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteam);
        init();
        addteam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String teamname = Teamname.getText().toString();
                String address = Address.getText().toString();
                String captain = Captainname.getText().toString();
                String contact = Contact.getText().toString();
                String teamemail = Email.getText().toString();
                String TeamId = playerRef.push().getKey();
                String CaptainId = captainRef.push().getKey();
                imageRef = storage.getReference("Team images/" + TeamId);
                uid=auth.getCurrentUser().getUid();


                uploaddata(teamname, address, teamemail, contact, captain, TeamId, CaptainId);
                Toast.makeText(Addteam.this, "Team added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Addteam.this,Addplayer.class));
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery(v);
            }
        });

    }

    private void uploaddata(final String teamname, final String address, final String teamemail, final String contact, final String captain, final String teamId, final String CaptainId) {

        BitmapDrawable drawable = (BitmapDrawable) Profile.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        imageRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if ((task.isSuccessful())) {
                            String imageUrl = task.getResult().toString();
                            getteamviewlist team = new getteamviewlist(imageUrl,teamname,address,contact,teamemail,captain,teamId);

                            playerRef.child(uid).child("Team").child(teamId).setValue(team);
                            captainRef.child(uid).child("Team").child(teamId).child("Captain").child(CaptainId).child("id").setValue(CaptainId);
                            captainRef.child(uid).child("Team").child(teamId).child("Captain").child(CaptainId).child("name").setValue(CaptainId);
                           /* captainRef.child(teamId).child("Captain").child(CaptainId).child("id").setValue(CaptainId);
                            captainRef.child(teamId).child("Captain").child(CaptainId).child("name").setValue(captain);
*/


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });

    }

    public void opengallery(View view) {

        Intent gallery = new Intent();
        gallery.setAction(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(Intent.createChooser(gallery, "Select"), Pick_image);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                Profile.setImageURI(imageUri);
            }
        }
    }

    private void init() {
        Contact = findViewById(R.id.Et_sign_contact);
        Teamname = findViewById(R.id.Et_sign_username);
        Address = findViewById(R.id.Et_sign_address);
        Email = findViewById(R.id.Et_sign_email);
        Captainname = findViewById(R.id.Et_sign_Captain);
        addteam = findViewById(R.id.btn_addteam);
        Profile = findViewById(R.id.team_img);
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        playerRef = firebaseDatabase.getReference("users");
        captainRef=playerRef;
    }
}
