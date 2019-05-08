package com.example.gu_rl.com.classSchedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gu_rl.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentUserScheduleFragment extends Fragment {


    public CurrentUserScheduleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_user_schedule, container, false);
    }

}
