package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
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

public class Addplayer extends AppCompatActivity {
    EditText playerName, Teamname, Playertype;
    ImageView Profile;

    private static final int Pick_image = 1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference playerRef;
    private Uri imageUri;
    ImageView profile;
    FirebaseStorage storage;
    StorageReference imageRef;
    Button addplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplayer);
        init();
        addplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameplayer = playerName.getText().toString();
                String nameteam = Teamname.getText().toString();
                String typeplayer = Playertype.getText().toString();


                uploadImage(nameplayer, nameteam, typeplayer);

            }
        });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery(v);
            }
        });
    }

    private void uploadImage(final String nameplayer, final String nameteam, final String typeplayer) {
        BitmapDrawable drawable = (BitmapDrawable) Profile.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();

        final String playerId = playerRef.push().getKey();
        imageRef = storage.getReference("Player images/" + playerId);
        imageRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if ((task.isSuccessful())) {
                            String imageUrl = task.getResult().toString();

                            Player player = new Player(playerId, nameplayer, nameteam, typeplayer, imageUrl);
                            Toast.makeText(Addplayer.this, "complete " + playerId, Toast.LENGTH_SHORT).show();
                            playerRef.child(playerId).setValue(player).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Addplayer.this, "added in db" + playerId, Toast.LENGTH_SHORT).show();
                                        //   startActivity(new Intent(Addplayer.this, PlayerList.class));
                                    } else
                                        Toast.makeText(Addplayer.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else Toast.makeText(Addplayer.this, task
                                .getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Addplayer.this, e.toString(), Toast.LENGTH_SHORT).show();
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
        playerName = findViewById(R.id.Et_Playername);
        Teamname = findViewById(R.id.Et_Teamname);
        Playertype = findViewById(R.id.Et_PlayerType);
        addplayer = findViewById(R.id.btn_addplayer);
        Profile = findViewById(R.id.profile_img);
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        playerRef = firebaseDatabase.getReference("Players");

    }
}
