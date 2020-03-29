package com.esq.e_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DataForMainCardTask} and makes a call to the
 * specified {@link ClickListener }.
 * TODO: Replace the implementation with code for your data type.
 */
public class MainCardTasksAdapter extends RecyclerView.Adapter<MainCardTasksAdapter.ViewHolder> {
    // Store a member variable for the task to be performed
    public static ClickListener clickListener;
    private List<DataForMainCardTask> dataForMainCardTasks;

    // Pass in the task array into the constructor
    public MainCardTasksAdapter(List<DataForMainCardTask> dataForMainCardTasks, ClickListener clickListener) {
        this.dataForMainCardTasks = dataForMainCardTasks;
        this.clickListener = clickListener;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MainCardTasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View taskView = inflater.inflate(R.layout.card_item_task, parent, false);
        // Return a new holder instance
        return new ViewHolder(taskView, clickListener);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MainCardTasksAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        DataForMainCardTask task = dataForMainCardTasks.get(position);
        // Set item views based on your views and data model
        TextView taskName = viewHolder.taskName;
        taskName.setText(task.getTaskName());
        TextView infoOnRemainingTasks = viewHolder.remainingTasks;
        infoOnRemainingTasks.setText(task.getNumberOfTasks() + " tasks left");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
            return dataForMainCardTasks != null ? dataForMainCardTasks.size() : 0;
    }

    public ArrayList<DataForMainCardTask> createTasksList(String taskText, String infoOnNumberOfTaskLeft ){
        this.notifyItemInserted(getItemCount());
        return DataForMainCardTask.createTasksList(taskText, infoOnNumberOfTaskLeft);
    }

    //Make the viewHolder implement OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        // Your holder should contain a member variable for any view that will be set as you render a row
        TextView taskName;
        TextView remainingTasks;
        ClickListener clickListener;
    // We also create a constructor that accepts the entire item row and does the view lookups to find each subview
    public ViewHolder(View itemView, ClickListener clickListener) {
        // Stores the itemView in a public final member variable that can be used to access the context from any ViewHolder instance.
        super(itemView);
        taskName = (TextView) itemView.findViewById(R.id.taskName);
        remainingTasks = (TextView) itemView.findViewById(R.id.numberOfTasksLeft);
        this.clickListener = clickListener;
        //Set a listener to the entire view or the view you want to listen for changes
        //Here we need to listen to the whole view
        itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

        public interface ClickListener {
            void onItemClick(int position, View v);
            void onItemLongClick(int position, View v);
    }
}

