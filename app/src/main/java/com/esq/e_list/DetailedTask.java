package com.esq.e_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DetailedTask extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    ImageButton imageButtonAddButton;
    ArrayList<DataForDetailedTask> dataForDetailedTaskArrayList;
    Context context = DetailedTask.this;
    static DetailedClassAdapter detailedClassAdapter;
    private static final String TAG = "DetailedTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);
        Log.d(TAG, "onCreate: Activity created");
        setUpToolBar();
        imageButtonAddButton = findViewById(R.id.imageButtonAddButton);
    }

    @Override
    public void onClick(View view) {
        if (view == imageButtonAddButton) {
            addButtonClickListener();
        }
    }

    public void setUpToolBar(){
        toolbar = findViewById(R.id.toolbar);
        //Set a navigation icon
        Drawable drawable = ContextCompat.getDrawable(DetailedTask.this, R.drawable.ic_arrow_back_black_24dp);
        toolbar.setTitle(CardTask.taskText);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        //Listener for navigationIcon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailedTask.this, CardTask.class);
                startActivity(intent);
            }
        });
    }
    //Inflate menu to bottom Bar: this adds the items to the action bar if present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailed_task_menu , menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    //handle click events of the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this, "onOptionsItemSelected: clicked", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.imageButtonAddButton:
                addButtonClickListener();
                Toast.makeText(this, "onOptionsItemSelected: Image Button clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Listener for clicking items in the Recycler View , it returns a listener used in adapter
    private DetailedTaskFragment.OnListFragmentInteractionListener setUpItemClickListener(){
        Log.d(TAG, "DetailedTaskFragment.OnListFragmentInteractionListener: setUpItemClickListener called");
        return new DetailedTaskFragment.OnListFragmentInteractionListener() {
            @Override
            public void onFragmentInteractionItemClick(int position, View v) {
                Log.d(TAG, "onFragmentInteractionItemClick position: " + position);
                Toast.makeText(DetailedTask.this, "Item " + position + " is clicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFragmentInteractionItemLongClick(int position, View v) {
                Log.d(TAG, "onFragmentInteractionItemLongClick position: " + position);
                Toast.makeText(DetailedTask.this, "Item " + position + " is long clicked", Toast.LENGTH_SHORT).show();
            }
        };
    }

    //Listener for clicking the add button
    public void addButtonClickListener(){
        Log.d(TAG, "addButtonClickListener: Image button Clicked");
                ArrayList<DataForDetailedTask> dummyTask;
                final DetailedClassAdapter detailedClassAdapter = new DetailedClassAdapter(dataForDetailedTaskArrayList, setUpItemClickListener());
                Log.d(TAG, "onFragmentAddButtonClickListener: Adding list");
                if (DataForDetailedTask.lastTaskId == 0){
                    dummyTask = DataForDetailedTask.createDetailedTasksList("");
                }
                else{
                    dummyTask = detailedClassAdapter.createDetailedTasksList("");
                }
                //Main Code
                dataForDetailedTaskArrayList.addAll(dummyTask);
                DetailedTaskFragment detailedTaskFragment = (DetailedTaskFragment) getSupportFragmentManager().findFragmentById(R.id.detailed_task_list);
                detailedTaskFragment.setUpRecyclerView();

    }
}

