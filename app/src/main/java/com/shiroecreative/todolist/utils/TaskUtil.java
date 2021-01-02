package com.shiroecreative.todolist.utils;


import com.shiroecreative.todolist.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskUtil {
    public static String processForSharing(Task task) {
        return "[TASK]\n" +
                "Title: " + task.getTitle() + "\n" +
                "Description : " + task.getDescription() + "\n" +
                "Date : " + task.getDate() + "\n";
    }

    public static List<Task> filterAvailable(List<Task> tasks) {
        final List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {
            if (!task.getChecked()) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    public static List<Task> filterCompleted(List<Task> tasks) {
        final List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getChecked()) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}
