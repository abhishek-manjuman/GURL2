package com.example.gu_rl;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gu_rl.com.common.StudentReg;
import com.example.gu_rl.com.common.UserLogin;
import com.example.gu_rl.com.session.CurrentUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class UserProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView userBio, userName, userEmail, userPhone, userCourse;
    private ImageView userProfileImage, addNewProfileImage;
    private Button btnLogout, btnViewDetails, btnUploadImage;
    private ProgressBar progressBar;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    private CurrentUser currentUser;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private UserLogin userLogin;

    private StorageTask storageTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FirebaseApp.initializeApp(this);

        storageReference = FirebaseStorage.getInstance().getReference("profile_image");
        databaseReference = FirebaseDatabase.getInstance().getReference("studentregistration");

        userLogin = new UserLogin();
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
        progressBar = (ProgressBar) findViewById(R.id.profile_image_upload_progressBar);
        btnUploadImage = (Button) findViewById(R.id.btn_upload_image);


        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(storageTask!=null && storageTask.isInProgress()){
                    Toasty.info(UserProfileActivity.this,"Image upload is in progress!", Toast.LENGTH_SHORT).show();
                }else {
                    uploadImage();
                }

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogout();
            }
        });
        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailsOfUser();
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

    private void viewDetailsOfUser() {

        Intent intent = new Intent(this,UserDetailsActivity.class);
        intent.putExtra("userid",1234);
        startActivity(intent);

    }

    private void openFileChosser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //{}
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imageUri = data.getData();
            userProfileImage.setImageURI(imageUri);
            btnUploadImage.setVisibility(View.VISIBLE);
        }
    }
    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadImage() {
        if(imageUri!=null){
            StorageReference fileRefrence = storageReference.child(System.currentTimeMillis()+""+getFileExtension(imageUri));

            storageTask = fileRefrence.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                    btnUploadImage.setVisibility(View.INVISIBLE);
                                }
                            }, 5000);

                            Toasty.success(UserProfileActivity.this,"Image upload successful!", Toast.LENGTH_SHORT).show();
                            StudentReg studentReg = new StudentReg("details",taskSnapshot.getUploadSessionUri().toString());
                            String uploadid = databaseReference.push().getKey();
                            databaseReference.child(uploadid).setValue(studentReg);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toasty.error(UserProfileActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int)progress);
                        }
                    });

        }else {
            Toasty.warning(this,"No file selected!", Toast.LENGTH_SHORT).show();
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
