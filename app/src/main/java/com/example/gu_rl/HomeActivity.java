package com.example.gu_rl;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gu_rl.com.common.SearchAdapter;
import com.example.gu_rl.com.session.CurrentUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Dialog searchbar;
    private CircularImageView circularImageView;
    private TextView editText;
    private ImageView closeSearchBar;

    private CardView basic_need, departments, higher_authority, faculty, classrooms;

    private CurrentUser currentUser;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        FirebaseApp.initializeApp(this);
//        databaseReference = FirebaseDatabase.getInstance().getReference("faculty");

        currentUser = new CurrentUser(getApplicationContext());

        String userId = currentUser.getCurrentUserId();

        circularImageView = (CircularImageView)findViewById(R.id.userProfile);
        if(currentUser.readLoginStatus()){
            circularImageView.setImageResource(R.drawable.raj);
        }else{
            circularImageView.setImageResource(R.drawable.ic_person);
        }

        editText = (TextView) findViewById(R.id.home_search);
        //closeSearchBar = (ImageView)findViewById(R.id.close_search_bar);

        basic_need = (CardView)findViewById(R.id.basic_need);
        departments = (CardView)findViewById(R.id.department);
        higher_authority = (CardView)findViewById(R.id.higher_authority);
        faculty = (CardView)findViewById(R.id.faculty);
        classrooms = (CardView)findViewById(R.id.classrooms);

        searchbar = new Dialog(this, R.style.DialogTheme);

        basic_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basicNeed();
            }
        });
        departments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                departments();
            }
        });
        higher_authority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                higherAuthority();
            }
        });
        faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                faculty();
            }
        });
        classrooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentUser.readLoginStatus()){
                    classrooms();
                }else{
                   startActivity( new Intent(HomeActivity.this, StudentLoginActivity.class));
                   finish();
                }

            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SearchBarActivity.class));
            }
        });
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    private void classrooms() {

        Intent intent = new Intent(this, ClassRoomsActivity.class);
        startActivity(intent);

    }

    private void faculty() {

        Intent intent = new Intent(this, FacultyActivity.class);
        startActivity(intent);

    }

    private void higherAuthority() {

        Intent intent = new Intent(this, HigherAuthorityActivity.class);
        startActivity(intent);
    }

    private void departments() {

        Intent intent = new Intent(this, DepartmentActivity.class);
        startActivity(intent);

    }

    private void basicNeed() {
        Intent intent = new Intent(this, BasicNeedActivity.class);
        startActivity(intent);
    }

    private void userLogin() {

        if (currentUser.readLoginStatus())
            startActivity(new Intent(this,UserProfileActivity.class));
        else
            startActivity(new Intent(this, StudentLoginActivity.class));

    }
}
