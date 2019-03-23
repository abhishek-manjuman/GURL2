package com.example.gu_rl;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gu_rl.com.session.CurrentUser;
import com.example.gu_rl.com.common.StudentReg;
import com.example.gu_rl.com.common.UserLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.dmoral.toasty.Toasty;

public class StudentRegistrationActivity extends AppCompatActivity {

    private Button button;
    private EditText enrollment, email, pass;
    private Button btnReg;
    private Toolbar toolbar;
    private CurrentUser currentUser;

    DatabaseReference databaseReference1, databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        FirebaseApp.initializeApp(this);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("studentregistration");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("userlogin");

        currentUser= new CurrentUser(getApplicationContext());
        button = findViewById(R.id.log_reg_btn);

        enrollment = findViewById(R.id.reg_enrolment);
        email = findViewById(R.id.reg_email);
        pass = findViewById(R.id.reg_pass);
        btnReg = findViewById(R.id.reg_btn);
        toolbar= findViewById(R.id.reg_toolbar);

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudents();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    String studentEnrollment;
    String studentEmail;
    String studentPassword;
    String ststus;
    String userType;
    String strDate;
    private void addStudents() {

        studentEnrollment= enrollment.getText().toString();
        studentEmail= email.getText().toString();
        studentPassword= pass.getText().toString();
        ststus = "true";
        userType = "student";

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        strDate = dateFormat.format(date).toString();

        if(!TextUtils.isEmpty(studentEnrollment) && !TextUtils.isEmpty(studentEmail) && !TextUtils.isEmpty(studentPassword)){

            final String idReg = databaseReference1.push().getKey();
            StudentReg studentReg = new StudentReg(idReg, studentEnrollment, studentEmail, studentPassword, strDate);

            databaseReference1.child(idReg).setValue(studentReg).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful()){
                    String idLogin = databaseReference2.push().getKey();
                    UserLogin userLogin = new UserLogin(idReg, studentEnrollment, studentEmail, studentPassword, ststus, userType,strDate);
                    databaseReference2.child(idLogin).setValue(userLogin);
                    Toasty.success(StudentRegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    currentUser.writeLoginStatus(true);
                    Intent intent = new Intent(StudentRegistrationActivity.this, StudentLoginActivity.class);
                    intent.putExtra("userId", idReg);
                    startActivity(intent);
                    finish();
                    }else {
                        Toasty.error(StudentRegistrationActivity.this,"You are not able to register",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            enrollment.setText("");
            email.setText("");
            pass.setText("");

        }else {
            Toasty.error(StudentRegistrationActivity.this,"Please Enter All Fields", Toast.LENGTH_SHORT).show();
        }

    }

    private void userLogin() {
        Intent intent = new Intent(this, StudentLoginActivity.class);
        startActivity(intent);
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
