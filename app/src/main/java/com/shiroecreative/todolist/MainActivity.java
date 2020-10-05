package com.shiroecreative.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TASK_TYPE = "task_type";
    public static final String TASK_NAME = "task_name";
    public static final String TASK_TIME = "task_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        intent.putExtra(TASK_TYPE, R.string.task_add_button);
        startActivity(intent);
    }
}