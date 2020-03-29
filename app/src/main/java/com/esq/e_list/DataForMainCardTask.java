package com.esq.e_list;

import java.util.ArrayList;

public class DataForMainCardTask {
        private String taskName;
        static int numberOfTasks;
        private String infoOnNumberOfTaskLeft;


        public DataForMainCardTask(String taskName, int numberOfTasks , String infoOnNumberOfTaskLeft){
            this.taskName = taskName;
            this.numberOfTasks = numberOfTasks;
            this.infoOnNumberOfTaskLeft = infoOnNumberOfTaskLeft;
        }

        public String getTaskName() {
            return taskName;
        }

        public int getNumberOfTasks() {
            return numberOfTasks;
        }

    public String getinfoOnNumberOfTaskLeft() {
        return infoOnNumberOfTaskLeft;
    }

        public static int lastTaskId = 0;
        public static ArrayList<DataForMainCardTask> createTasksList(String taskText, String infoOnNumberOfTaskLeft ){
            ArrayList<DataForMainCardTask> dataForMainCardTasks = new ArrayList<DataForMainCardTask>();
            dataForMainCardTasks.add(new DataForMainCardTask(taskText , numberOfTasks, infoOnNumberOfTaskLeft));
            ++lastTaskId;
            return dataForMainCardTasks;
        }

}
