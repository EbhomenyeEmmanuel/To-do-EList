package com.esq.e_list;

import java.util.ArrayList;

public class DataForDetailedTask {
    private String taskName;
    private int taskNumber;
    public DataForDetailedTask(String taskName, int taskNumber){
        this.taskName = taskName;
        this.taskNumber = taskNumber;
    }
    public DataForDetailedTask(String taskName){
        this(taskName, 0);
    }
    public String getTaskName() {
        return taskName;
    }
    public static int lastTaskId = 0;
    public static ArrayList<DataForDetailedTask> createDetailedTasksList(String taskText){
        ArrayList<DataForDetailedTask> mainTasks = new ArrayList<>();
        mainTasks.add(new DataForDetailedTask(taskText));
        ++lastTaskId;
        return mainTasks;
    }
}
