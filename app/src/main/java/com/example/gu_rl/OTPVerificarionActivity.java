package com.example.gu_rl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class OTPVerificarionActivity extends AppCompatActivity {


    private Toolbar toolbar;
    private Button btnVerify;
    private EditText otpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverificarion);

        toolbar = (Toolbar)findViewById(R.id.otp_toolbar);
        btnVerify = (Button)findViewById(R.id.btn_verify);
        otpText = (EditText)findViewById(R.id.txt_otp);

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyOTP();
            }
        });

    }

    private String otp;
    private String otpEmail;
    private void verifyOTP() {

        otp = otpText.getText().toString();
        //{}
        if(otp.isEmpty()){
            Toasty.error(this,"Please enter your otp", Toast.LENGTH_SHORT).show();
        }else {
           otpEmail = getIntent().getStringExtra("otp");
           if(otp.equals(otpEmail)){
               startActivity(new Intent(this,ChangePasswordActivity.class));
               otpText.setText("");
           }else {
               Toasty.info(this,"Please enter correct OTP!", Toast.LENGTH_SHORT).show();
           }
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
