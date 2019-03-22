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

import com.example.gu_rl.com.common.UserLogin;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import es.dmoral.toasty.Toasty;

public class ForgetPassActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private EditText regEmail;
    private Button btnSearch;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("userlogin");

        toolbar = (Toolbar)findViewById(R.id.forget_pass_toolbar);
        regEmail = (EditText) findViewById(R.id.txt_forget_pass_email);
        btnSearch = (Button) findViewById(R.id.btn_forget_pass_search);
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEmail();
            }
        });


    }


    private String email;
    Random rnd = new Random();
    //int otp = 100000 + rnd.nextInt(999999);
    int p=0;
    private void searchEmail() {

        email = regEmail.getText().toString();

        //{}
        if(email.isEmpty()){
            Toasty.error(this,"Please enter your email!", Toast.LENGTH_SHORT).show();
        }else {

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){

                        UserLogin log = userSnapshot.getValue(UserLogin.class);
                        if(email.equals(log.getEmail())){

                            p++;
                            Intent intent= new Intent(ForgetPassActivity.this, OTPVerificarionActivity.class);
                            intent.putExtra("otp","123456");
                            startActivity(intent);
                        }
                    }
                    if (p<=0)
                        Toasty.error(ForgetPassActivity.this,"Please enter correct email!", Toast.LENGTH_SHORT).show();

                    regEmail.setText("");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
