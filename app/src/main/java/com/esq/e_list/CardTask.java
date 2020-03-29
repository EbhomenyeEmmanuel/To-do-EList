package com.esq.e_list;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CardTask extends AppCompatActivity {
    //Set bottomAppBar
    BottomAppBar bottomAppBar;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<DataForMainCardTask> listOfTask;//List of task in activity
    static String taskText;
    Context context = CardTask.this;
    static MainCardTasksAdapter adapter;
    static int numberOfTasks = CardTask.numberOfTasks;
    private static final String TAG = "CardTask";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_card_item_task);
        //Set up bottom bar
        //bottomAppBar = findViewById(R.id.bottom_app_bar);
        //Set bottom bar to Action bar as it is similar like toolbar
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        setUpFab();
        setUpBottomAppBar();
    }

   //Set up bottom bar
    private void setUpBottomAppBar() {
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        //Set bottom bar to Action bar as it is similar like toolbar
        setSupportActionBar(bottomAppBar);
        //Click event over Bottom bar navigation item
        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v , "Navigation icon is clicked", Snackbar.LENGTH_LONG)
                .setAction("UNDO", null)
                        .show();
            }
        });
    }

    //Set up fab
    private void setUpFab(){
        FloatingActionButton fab = findViewById(R.id.fab);
        listOfTask = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(context);
                final String infoOnNumberOfTaskLeft = " tasks remaining";
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Categories Of Tasks")
                        .setMessage("Add New Task i.e Personal Task  :)")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                taskText = String.valueOf(taskEditText.getText());
                                // Initialize tasks
                                if (taskText.length() < 1){
                                    //Create another xml file that shows motivational images
                                }else{
                                    ArrayList<DataForMainCardTask> dummyTask;
                                   if (DataForMainCardTask.lastTaskId == 0){
                                        dummyTask = DataForMainCardTask.createTasksList(taskText, infoOnNumberOfTaskLeft);
                                    }
                                    else{
                                        dummyTask = adapter.createTasksList(taskText, infoOnNumberOfTaskLeft);
                                    }
                                    //Main Code
                                    listOfTask.addAll(dummyTask);
                                }

                                // Create adapter passing in the sample user data
                                adapter = new MainCardTasksAdapter(listOfTask, setUpItemClickListener());
                                //Set up RecyclerView
                                setUpRecyclerView(adapter);

                            }
                        })
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create();
        dialog.show();
            }
        });
    }
    //Inflate menu to bottom Bar: this adds the items to the action bar if present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_app_bar_menu , menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    //handle click events of the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.app_bar_settings:
                Toast.makeText(this, "onOptionsItemSelected: Settings icon Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Set up recyclerView
    private void setUpRecyclerView(MainCardTasksAdapter adapter) {
        //recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        //Add layout before setting adapter to avoid "RecyclerView: No adapter attached; skipping layout" error

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Attach the adapter to the recyclerview to populate items
        recyclerView.setAdapter(adapter);
        recyclerView.getAdapter().notifyDataSetChanged();
        //dismiss bottom bar if recyclerView is scrolled
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && recyclerView.isShown()) {
                 bottomAppBar.animate().alpha(0.0f).setDuration(3000);
                 bottomAppBar.setVisibility(View.GONE);
                } else if (dy < 0 || !bottomAppBar.isShown()) {
                    bottomAppBar.animate().alpha(1.0f).setDuration(3000);
                    bottomAppBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    //Set up click listener
    private MainCardTasksAdapter.ClickListener setUpItemClickListener(){
        return new MainCardTasksAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.d(TAG, "onItemClick position: " + position);
                Intent intent = new Intent(CardTask.this, DetailedTask.class);
                startActivity(intent);
                Toast.makeText(CardTask.this, "onItemClick position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.d(TAG, "onItemLongClick pos = " + position);
            }
        };
    }

}
