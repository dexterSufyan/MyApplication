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

import com.google.android.gms.tasks.OnCompleteListener;
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

public class AddEvent extends AppCompatActivity {
    EditText Eventname,Url;
    ImageView Profile;
    private static final int Pick_image = 1;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser user;
    DatabaseReference EventRef;
    private Uri imageUri;
    //ImageView profile;
    FirebaseStorage storage;
    StorageReference imageRef;
    Button addEvent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
    init();
    addEvent.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String eventname= Eventname.getText().toString();
            String url= Url.getText().toString();
            String EventId=EventRef.push().getKey();
            imageRef=storage.getReference("Event images/"+EventId);
            uploaddata(eventname,url,EventId);
            Toast.makeText(AddEvent.this, "Added Event", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddEvent.this,onlinemain.class));
        }
    });
        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery(v);
            }
        });


    }

    private void uploaddata(final String eventname, final String url, final String eventId) {
        BitmapDrawable drawable=(BitmapDrawable) Profile.getDrawable();
        Bitmap bitmap=drawable.getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes=stream.toByteArray();
        imageRef.putBytes(bytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
           imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
               @Override
               public void onComplete(@NonNull Task<Uri> task) {
                   if ((task.isSuccessful())) {
                       String imageUrl = task.getResult().toString();
                       geteventviewlist event= new geteventviewlist(imageUrl,eventname,url,eventId);
                               EventRef.child(eventId).setValue(event);
                       //startActivity(new Intent(Addplayer.this, PlayerList.class));
                   }
               }});
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
        Eventname=findViewById(R.id.et_eventname);
        Url=findViewById(R.id.et_url);
        addEvent=findViewById(R.id.btn_addevent);
        Profile=findViewById(R.id.Img);
        storage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        EventRef=firebaseDatabase.getReference("Events");

    }
}
