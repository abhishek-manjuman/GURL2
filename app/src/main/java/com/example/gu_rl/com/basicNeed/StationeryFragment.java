package com.example.gu_rl.com.basicNeed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gu_rl.R;

import net.cachapa.expandablelayout.ExpandableLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class StationeryFragment extends Fragment implements View.OnClickListener {


    private ExpandableLayout expandableLayout0;
    private ExpandableLayout expandableLayout1;
    private ExpandableLayout expandableLayout2;

    public StationeryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stationery, container, false);

        expandableLayout0 = rootView.findViewById(R.id.expandable_layout_3);
        expandableLayout1 = rootView.findViewById(R.id.expandable_layout_4);
        expandableLayout2 = rootView.findViewById(R.id.expandable_layout_5);

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

        rootView.findViewById(R.id.expand_button_3).setOnClickListener(this);
        rootView.findViewById(R.id.expand_button_4).setOnClickListener(this);
        rootView.findViewById(R.id.expand_button_5).setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.expand_button_3) {
            expandableLayout0.expand();
            expandableLayout1.collapse();
            expandableLayout2.collapse();
        }else if (view.getId() == R.id.expand_button_4) {
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
