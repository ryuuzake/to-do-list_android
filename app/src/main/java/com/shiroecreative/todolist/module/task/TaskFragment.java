package com.shiroecreative.todolist.module.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;

public class TaskFragment extends BaseFragment<TaskActivity, TaskContract.Presenter> implements TaskContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_task, container, false);
        presenter.start();

        return fragmentView;
    }

    @Override
    public void setupEditTask() {
        final TextView taskTitle = fragmentView.findViewById(R.id.task_title_textView);
        final Button taskEditButton = fragmentView.findViewById(R.id.task_edit_button);
        final Button taskDeleteButton = fragmentView.findViewById(R.id.task_delete_button);

        setTitle(getResources().getString(R.string.task_title_edit));
        taskTitle.setText(R.string.task_title_edit);
        taskEditButton.setVisibility(View.VISIBLE);
        taskDeleteButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setupAddTask() {
        final TextView taskTitle = fragmentView.findViewById(R.id.task_title_textView);
        final Button taskAddButton = fragmentView.findViewById(R.id.task_add_button);

        setTitle(getResources().getString(R.string.task_title_add));
        taskTitle.setText(R.string.task_title_add);
        taskAddButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(TaskContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
