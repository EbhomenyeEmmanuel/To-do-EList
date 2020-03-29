package com.esq.e_list;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailedTaskFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    TextView detailedTaskName;
    RecyclerView recyclerView;
    private static OnListFragmentInteractionListener mListener;
    private static final String TAG = "DetailedTaskFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_task_list, container, false);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: Fragment has been started");
        super.onStart();
        View view = getView();
        Log.d(TAG, view == null ?"onStart: View is null" :"View is not null, views initialized.");
        if(view!= null){
            detailedTaskName = view.findViewById(R.id.detailedTaskName);
            recyclerView = (RecyclerView) view.findViewById(R.id.detailedListRecyclerView);
            // TODO: Update the name of different activities using the same fragment
           // title.setText(workout.getName());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }/*
         *else {
            throw new RuntimeException(context.toString() + " must implement Listener" );
        }
        */
    }
    //Set up recyclerView
    public void setUpRecyclerView() {
       // recyclerView = (RecyclerView) view.findViewById(R.id.detailedListRecyclerView);
        //Add layout before setting adapter to avoid "RecyclerView: No adapter attached; skipping layout" error
         recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(DetailedTask.detailedClassAdapter);
        Log.d(TAG, "setUpRecyclerView: Setting Up Recycler View in detailed Fragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        Log.d(TAG, "onDetach: Listeners has been detached");
    }

    //Adding a listener to the fragment
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteractionItemClick(int position, View v);
        void onFragmentInteractionItemLongClick(int position, View v);
    }

}