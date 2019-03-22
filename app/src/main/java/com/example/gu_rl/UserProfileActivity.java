package com.example.gu_rl;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gu_rl.com.common.UserLogin;
import com.example.gu_rl.com.session.CurrentUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class UserProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView userBio, userName, userEmail, userPhone, userCourse;
    private ImageView userProfileImage, addNewProfileImage;
    private Button btnLogout, btnViewDetails;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    private CurrentUser currentUser;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FirebaseApp.initializeApp(this);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("userlogin");

        UserLogin userLogin = new UserLogin();
        currentUser = new CurrentUser(getApplicationContext());

        toolbar = (Toolbar)findViewById(R.id.profile_toolbar);
        userBio = (TextView)findViewById(R.id.user_profile_bio);
        userName = (TextView)findViewById(R.id.user_name);
        userEmail = (TextView)findViewById(R.id.user_email);
        userPhone = (TextView)findViewById(R.id.user_phone);
        userCourse = (TextView)findViewById(R.id.user_course);
        btnLogout = (Button) findViewById(R.id.btn_user_profile_logout);
        btnViewDetails = (Button) findViewById(R.id.btn_user_profile_view_details);
        userProfileImage = (ImageView) findViewById(R.id.user_profile_image);
        addNewProfileImage = (ImageView) findViewById(R.id.add_new_profile_image);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });

        addNewProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChosser();
            }
        });

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void openFileChosser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //{}
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null){

            imageUri = data.getData();

            userProfileImage.setImageURI(imageUri);
            if(imageUri !=null){
                StorageReference fileReference = storageReference.child("user_profile_image/"+
                        System.currentTimeMillis()+"."+ getFileExtension(imageUri));
            }else {
                Toasty.info(this, "No file selected!", Toast.LENGTH_LONG).show();
            }


        }
    }

    private void userLogout() {
        currentUser.writeLoginStatus(false);
        startActivity(new Intent(this,HomeActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
