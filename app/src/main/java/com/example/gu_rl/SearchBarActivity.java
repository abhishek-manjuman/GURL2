package com.example.gu_rl;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gu_rl.com.common.SearchAdapter;
import com.example.gu_rl.com.session.CurrentUser;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchBarActivity extends AppCompatActivity {

    private RecyclerView recyclerViewSearchItem;
    private Toolbar toolbar;
    private EditText txtSearch;
    private DatabaseReference databaseReference;

    SearchAdapter searchAdapter;

    ArrayList<String> fullNameList;
    ArrayList<String> firstNameList;
    ArrayList<String> lastNameList;
    ArrayList<String> departmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);

        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("faculty");
        toolbar = (Toolbar)findViewById(R.id.search_bar_toolbar);

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            recyclerViewSearchItem = (RecyclerView) findViewById(R.id.recyclerViewSearchItem);
            txtSearch = (EditText) findViewById(R.id.txtSearchItem);

            recyclerViewSearchItem.setHasFixedSize(true);
            recyclerViewSearchItem.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewSearchItem.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

            fullNameList = new ArrayList<>();
            firstNameList = new ArrayList<>();
            lastNameList = new ArrayList<>();
            departmentList = new ArrayList<>();

            txtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if(s.toString().isEmpty()){

                        fullNameList.clear();
                        firstNameList.clear();
                        lastNameList.clear();
                        departmentList.clear();
                        recyclerViewSearchItem.removeAllViews();

                    }else {
                        setAdapter(s.toString());
                    }

                }

                @Override
                public void afterTextChanged(Editable s) {
//{}
//                    if(s.toString().isEmpty()){
//
//                        setAdapter(s.toString());
//
//                    }else {
//                        fullNameList.clear();
//                        firstNameList.clear();
//                        lastNameList.clear();
//                        departmentList.clear();
//                        recyclerViewSearchItem.removeAllViews();
//                    }
                }
            });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setAdapter(final String string) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullNameList.clear();
                firstNameList.clear();
                lastNameList.clear();
                departmentList.clear();
                recyclerViewSearchItem.removeAllViews();
                int counter = 0;

                for(DataSnapshot data : dataSnapshot.getChildren()){
                    String uid = data.getKey();
                    String fullName = data.child("fulname").getValue(String.class);
                    String firstName = data.child("firstName").getValue(String.class);
                    String lastName = data.child("lastName").getValue(String.class);
                    String departmentName = data.child("department").getValue(String.class);

                    if(fullName.toLowerCase().contains(string.toLowerCase()) || fullName.toUpperCase().contains(string.toUpperCase())){
                        fullNameList.add(fullName);
                        firstNameList.add(firstName);
                        lastNameList.add(lastName);
                        departmentList.add(departmentName);
                        counter++;
                    }else if(firstName.toLowerCase().contains(string.toLowerCase()) || firstName.toUpperCase().contains(string.toUpperCase())){
                        fullNameList.add(fullName);
                        firstNameList.add(firstName);
                        lastNameList.add(lastName);
                        departmentList.add(departmentName);
                        counter++;
                    }else if(lastName.toLowerCase().contains(string.toLowerCase()) || lastName.toUpperCase().contains(string.toUpperCase())){
                        fullNameList.add(fullName);
                        firstNameList.add(firstName);
                        lastNameList.add(lastName);
                        departmentList.add(departmentName);
                        counter++;
                    }

                    if(counter==15)
                        break;
                }

                searchAdapter = new SearchAdapter(SearchBarActivity.this,fullNameList,firstNameList,lastNameList,departmentList);
                recyclerViewSearchItem.setAdapter(searchAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
