package com.esq.e_list;

import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.esq.e_list.DetailedTaskFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DataForDetailedTask} and makes a call to the
 * specified {@link OnListFragmentInteractionListener and @link OnClickAddTaskButtonListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DetailedClassAdapter extends RecyclerView.Adapter<DetailedClassAdapter.ViewHolder> {

    private final List<DataForDetailedTask> detailedTaskList;
    private OnListFragmentInteractionListener onListFragmentInteractionListener;

    public DetailedClassAdapter(List<DataForDetailedTask> detailedTaskList, OnListFragmentInteractionListener mListener) {
        this.detailedTaskList = detailedTaskList;
        this.onListFragmentInteractionListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_detailed_task, parent, false);
        return new ViewHolder(view, onListFragmentInteractionListener);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Get the data model based on position
        DataForDetailedTask task = detailedTaskList.get(position);
        holder.mContentView.setText(task.getTaskName());
    }

    @Override
    public int getItemCount() {
        return detailedTaskList != null ? detailedTaskList.size() : 0;
    }

    public ArrayList<DataForDetailedTask> createDetailedTasksList(String taskText){
        this.notifyItemInserted(getItemCount());
        return DataForDetailedTask.createDetailedTasksList(taskText);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView mContentView;//Text in list
        DetailedTaskFragment.OnListFragmentInteractionListener onListFragmentInteractionListener;
        public ViewHolder(View view, DetailedTaskFragment.OnListFragmentInteractionListener onListFragmentInteractionListener) {
            super(view);
            mContentView = (TextView) view.findViewById(R.id.content);
            this.onListFragmentInteractionListener = onListFragmentInteractionListener;
            view.setOnClickListener(this);
        }

        //Implement the various listener methods in DetailedTask Class
        @Override
        public void onClick(View v) {
            if (v == mContentView){
               onListFragmentInteractionListener.onFragmentInteractionItemClick(getAdapterPosition(), v);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (v == mContentView) onListFragmentInteractionListener.onFragmentInteractionItemLongClick(getAdapterPosition(), v);
            return false;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
