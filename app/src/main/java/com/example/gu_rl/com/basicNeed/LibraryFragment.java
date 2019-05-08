package com.example.gu_rl.com.basicNeed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gu_rl.BasicNeedActivity;
import com.example.gu_rl.MainActivity;
import com.example.gu_rl.R;

import net.cachapa.expandablelayout.ExpandableLayout;

public class LibraryFragment extends Fragment {

    View view;
    boolean onn = false;

    private ImageView aBlockLibrary,aBlockLibrary2,bBlockLibrary;
    private ImageView aLibraryDirection,aLibrary2Direction,bLibraryDirection;
    private ExpandableLayout expandableLayout0,expandableLayout1,expandableLayout2;
    public LibraryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           view = inflater.inflate(R.layout.library_fragment, container, false);

        expandableLayout0= (ExpandableLayout)view.findViewById(R.id.expandable_direction1);
        expandableLayout1= (ExpandableLayout)view.findViewById(R.id.expandable_direction2);
        expandableLayout2= (ExpandableLayout)view.findViewById(R.id.expandable_direction3);

        aBlockLibrary = (ImageView)view.findViewById(R.id.a_block_library_direction);
        aBlockLibrary2 = (ImageView)view.findViewById(R.id.a_block_library2_direction);
        bBlockLibrary = (ImageView)view.findViewById(R.id.b_block_library_direction);
//
//        expandableLayout0.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
//            @Override
//            public void onExpansionUpdate(float expansionFraction, int state) {
//                // Log.d("ExpandableLayout0", "State: " + state);
//            }
//        });
//
//        expandableLayout1.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
//            @Override
//            public void onExpansionUpdate(float expansionFraction, int state) {
//                //Log.d("ExpandableLayout1", "State: " + state);
//            }
//        });
//        expandableLayout2.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener() {
//            @Override
//            public void onExpansionUpdate(float expansionFraction, int state) {
//                //Log.d("ExpandableLayout1", "State: " + state);
//            }
//        });

        aBlockLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onn){
                    expandableLayout0.collapse();
                    onn= false;
                }else {
                expandableLayout0.expand();
                onn = true;
                }
            }
        });
        aBlockLibrary2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onn){
                    expandableLayout1.collapse();
                    onn= false;
                }else {
                    expandableLayout1.expand();
                    onn = true;
                }
            }
        });
        bBlockLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onn){
                    expandableLayout2.collapse();
                    onn= false;
                }else {
                    expandableLayout2.expand();
                    onn = true;
                }
            }
        });

        return view;
    }

}