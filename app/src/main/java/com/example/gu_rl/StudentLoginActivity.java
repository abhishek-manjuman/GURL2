package com.example.gu_rl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gu_rl.com.session.CurrentUser;
import com.example.gu_rl.com.common.UserLogin;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class StudentLoginActivity extends AppCompatActivity {

    private Button btnLogin, btnSignup;
    private Toolbar toolbar;
    private TextView forgetPass;
    private EditText logEmail, logPass;

    private CurrentUser currentUser;


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);


        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("userlogin");

        currentUser = new CurrentUser(getApplicationContext());

        if(currentUser.readLoginStatus()){

            Intent intent = new Intent(StudentLoginActivity.this, UserProfileActivity.class);
            startActivity(intent);
        }

        btnSignup = (Button) findViewById(R.id.btnSignup);
        toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        logEmail = (EditText)findViewById(R.id.login_email);
        logPass = (EditText)findViewById(R.id.login_pass);
        forgetPass = (TextView)findViewById(R.id.forgetPass);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               userLogin();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRegistration();
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLoginActivity.this, ForgetPassActivity.class));
            }
        });

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    String pass,email;
    int match_email_pass,match_userStatus;
    private void userLogin() {

         match_email_pass=0;
         match_userStatus=0;
         email = logEmail.getText().toString();
         pass = logPass.getText().toString();

         if(!email.isEmpty() && !pass.isEmpty()){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot userData : dataSnapshot.getChildren()) {

                    UserLogin log = userData.getValue(UserLogin.class);
//{}
                   if(email.equals(log.getEmail()) && pass.equals(log.getPassword())){
                       match_email_pass++;
                       if (log.getUserType().equals("student")){
                           if (log.getStatus().equals("true")){
                               match_userStatus++;
                               currentUser.writeLoginStatus(true, log.getId());
                               Toasty.success(StudentLoginActivity.this, "You are successfully Login.", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(StudentLoginActivity.this, HomeActivity.class);
                               intent.putExtra("userId", log.getId());
                               startActivity(intent);
                               finish();
                           }
                       }

                  }
                }

                logEmail.setText("");
                logPass.setText("");
                if(match_email_pass<=0 ){
                    Toasty.error(StudentLoginActivity.this, "Enter correct Id or Password!", Toast.LENGTH_SHORT).show();
                }else if (match_userStatus<=0){
                    Toasty.warning(StudentLoginActivity.this, "Your account is deactivated!", Toast.LENGTH_SHORT).show();
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         }else {
             Toasty.error(StudentLoginActivity.this, "Please Enter your Id and Password both!", Toast.LENGTH_SHORT).show();
         }
    }

    private void userRegistration() {

        Intent intent = new Intent(this,StudentRegistrationActivity.class);
        startActivity(intent);
        finish();

    }

}
