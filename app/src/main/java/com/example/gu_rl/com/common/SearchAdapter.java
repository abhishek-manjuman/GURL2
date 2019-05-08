package com.example.gu_rl.com.common;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gu_rl.FacultyProfileActivity;
import com.example.gu_rl.R;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    Context context;
    ArrayList<String> fullNameList;
    ArrayList<String> firstNameList;
    ArrayList<String> lastNameList;
    ArrayList<String> departmentList;
    ArrayList<String> facultyIdList;
    View view;

    class SearchViewHolder extends RecyclerView.ViewHolder{

        TextView searchItemHeading;
        TextView searchItemSubHeading;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchItemHeading = (TextView)itemView.findViewById(R.id.search_item_heading);
            searchItemSubHeading = (TextView)itemView.findViewById(R.id.search_item_sub_heading);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> fullNameList, ArrayList<String> firstNameList, ArrayList<String> lastNameList, ArrayList<String> departmentList, ArrayList<String> facultyIdList) {
        this.context = context;
        this.fullNameList = fullNameList;
        this.firstNameList = firstNameList;
        this.lastNameList = lastNameList;
        this.departmentList = departmentList;
        this.facultyIdList = facultyIdList;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_layout, viewGroup, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {

            searchViewHolder.searchItemHeading.setText(fullNameList.get(i));
            searchViewHolder.searchItemSubHeading.setText(departmentList.get(i));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

    }

    @Override
    public int getItemCount() {
        return fullNameList.size();
    }
}
