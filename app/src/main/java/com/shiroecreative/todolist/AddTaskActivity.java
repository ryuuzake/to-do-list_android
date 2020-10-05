package com.shiroecreative.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setupView();
    }

    private void setupView() {
        Intent intent = getIntent();
        int taskType = intent.getIntExtra(MainActivity.TASK_TYPE, 0);
        if (taskType == R.string.task_edit_button) {
            setupEditTask(intent);
        } else {
            setupAddTask();
        }
    }

    private void setupEditTask(Intent intent) {
        final TextView taskTitle = findViewById(R.id.task_title_textView);
        final Button taskEditButton = findViewById(R.id.task_edit_button);
        final Button taskDeleteButton = findViewById(R.id.task_delete_button);

        taskTitle.setText(R.string.task_title_edit);
        taskEditButton.setVisibility(View.VISIBLE);
        taskDeleteButton.setVisibility(View.VISIBLE);
    }

    private void setupAddTask() {
        final TextView taskTitle = findViewById(R.id.task_title_textView);
        final Button taskAddButton = findViewById(R.id.task_add_button);

        taskTitle.setText(R.string.task_title_add);
        taskAddButton.setVisibility(View.VISIBLE);
    }
}