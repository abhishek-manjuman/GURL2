package com.example.gu_rl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ChangePasswordActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText txtNewPass, txtConfirmPass;
    private Button btnChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = (Toolbar)findViewById(R.id.change_pass_toolbar);
        txtNewPass = (EditText)findViewById(R.id.txt_new_pass);
        txtConfirmPass = (EditText)findViewById(R.id.txt_confirm_pass);
        btnChangePass = (Button) findViewById(R.id.btnChangePass);

        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void changePassword() {

        String newPass = txtNewPass.getText().toString();
        String confirmPass = txtConfirmPass.getText().toString();
        //{}
        if(newPass.isEmpty() && confirmPass.isEmpty()){
            Toasty.error(this,"Please enter both fields!", Toast.LENGTH_SHORT).show();
        }else {
            if(newPass.equals(confirmPass)){

            }else {
                Toasty.info(this,"Password should must be same!", Toast.LENGTH_SHORT).show();
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
