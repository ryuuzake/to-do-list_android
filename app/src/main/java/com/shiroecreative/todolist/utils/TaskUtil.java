package com.shiroecreative.todolist.utils;


import com.shiroecreative.todolist.data.model.Task;

public class TaskUtil {
    public static String processForSharing(Task task) {
        return "[TASK]\n" +
                "Title: " + task.getTitle() + "\n" +
                "Description : " + task.getDescription() + "\n" +
                "Date : " + task.getDate() + "\n";
    }
}
