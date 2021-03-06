package com.example.gu_rl.com.basicNeed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gu_rl.R;

import net.cachapa.expandablelayout.ExpandableLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeminarHallFragment extends Fragment implements View.OnClickListener{



    private ExpandableLayout expandableLayout0;
    private ExpandableLayout expandableLayout1;
    private ExpandableLayout expandableLayout2;

    public SeminarHallFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seminar_hall, container, false);

        expandableLayout0 = rootView.findViewById(R.id.expandable_layout_0);
        expandableLayout1 = rootView.findViewById(R.id.expandable_layout_1);
        expandableLayout2 = rootView.findViewById(R.id.expandable_layout_2);

        expandableLayout0.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
               // Log.d("ExpandableLayout0", "State: " + state);
            }
        });

        expandableLayout1.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                //Log.d("ExpandableLayout1", "State: " + state);
            }
        });
        expandableLayout2.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                //Log.d("ExpandableLayout1", "State: " + state);
            }
        });

        rootView.findViewById(R.id.expand_button_0).setOnClickListener(this);
        rootView.findViewById(R.id.expand_button_1).setOnClickListener(this);
        rootView.findViewById(R.id.expand_button_2).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.expand_button_0) {
            expandableLayout0.expand();
            expandableLayout1.collapse();
            expandableLayout2.collapse();
        }else if (view.getId() == R.id.expand_button_1) {
            expandableLayout1.expand();
            expandableLayout0.collapse();
            expandableLayout2.collapse();
        } else {
            expandableLayout0.collapse();
            expandableLayout1.collapse();
            expandableLayout2.expand();
        }

    }
}
