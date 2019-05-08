package com.example.gu_rl;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DepartmentActivity extends AppCompatActivity {


    private Toolbar toolbar;

    private Spinner programSpinner;
    private Spinner schoolSpinner;
    private Spinner courseSpinner;
    private TextView departmentDetailsTV;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        toolbar = findViewById(R.id.department_toolbar);
        programSpinner = findViewById(R.id.select_programs);
        schoolSpinner = findViewById(R.id.select_schools);
        courseSpinner = findViewById(R.id.select_courses);
        departmentDetailsTV = findViewById(R.id.department_details);

        setSupportActionBar(toolbar);
        //toolbar.setTitle("Basic Need");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        schoolSpinner.setEnabled(false);
        schoolSpinner.setAdapter(adapter);
        courseSpinner.setAdapter(adapter);

        programSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Select Program")) {
                    schoolSpinner.setEnabled(false);
                    List<String> list = new ArrayList<String>();
                    list.add("Select School");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Diploma Programs")) {
                    schoolSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Select School");
                    list.add("Diploma in Engineering");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Doctoral Programs")) {
                    schoolSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Select School");
                    list.add("School of Basic Applied & Sciences");
                    list.add("School of Business");
                    list.add("School of Chemical Engineering");
                    list.add("School of Civil Engineering");
                    list.add("School of Computing Science & Engineering");
                    list.add("School of Education");
                    list.add("School of Electrical, Electronics & Communication Engineering");
                    list.add("School of Law");
                    list.add("School of Liberal Education");
                    list.add("School of Mechanical Engineering");
                    list.add("School of Media & Communication Studies");
                    list.add("School of Medical & Allied Sciences");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Integrated Programs")) {
                    schoolSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Select School");
                    list.add("Salary2");
                    list.add("Sales2");
                    list.add("Others2");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Post Graduate Programs")) {
                    schoolSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Select School");
                    list.add("Salary3");
                    list.add("Sales3");
                    list.add("Others3");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Under Graduate Programs")) {
                    schoolSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Salary4");
                    list.add("Sales4");
                    list.add("Others4");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    schoolSpinner.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        schoolSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Select School")) {
                    courseSpinner.setEnabled(false);
                    List<String> list = new ArrayList<String>();
                    list.add("Select Course");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    courseSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("Diploma in Engineering")) {
                    courseSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Select Course");
                    list.add("Diploma in Automobile Engineering");
                    list.add("Diploma in Business Administration");
                    list.add("Diploma in Chemical Engineering");
                    list.add("Diploma in Civil Engineering");
                    list.add("Diploma in Computer Science & Engineering");
                    list.add("Diploma in Construction Management & Engineering");
                    list.add("Diploma in Electricle Engineering");
                    list.add("Diploma in Electronice & Communication Engineering");
                    list.add("Diploma in Food Technology");
                    list.add("Diploma in Instrumentation");
                    list.add("Diploma in Mechanicle Engineering");
                    list.add("Diploma in Pharmacy");
                    list.add("Diploma in Production Engineering");
                    list.add("Diploma in Refrigration & Air Conditioning");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    courseSpinner.setAdapter(dataAdapter);
                }

                if(parent.getItemAtPosition(position).toString().equals("School of Basic Applied & Sciences")) {
                    courseSpinner.setEnabled(true);
                    List<String> list = new ArrayList<String>();
                    list.add("Doctor of Philosophy (Ph.D.) in Bio-Chemistry");
                    list.add("Doctor of Philosophy (Ph.D.) in Chemistry");
                    list.add("Doctor of Philosophy (Ph.D.) in Forensic Science");
                    list.add("Doctor of Philosophy (Ph.D.) in Physics");
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(DepartmentActivity.this, android.R.layout.simple_list_item_1, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    dataAdapter.notifyDataSetChanged();
                    courseSpinner.setAdapter(dataAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Diploma in Automobile Engineering")) {
                    departmentDetailsTV.setText("Automobile Engineering is a specialized branch of mechanical engineering, covering the areas of automobile engines, transmission, suspension, braking systems, electrical and electronics, comfort and safety, automotive workshop technology and management.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
