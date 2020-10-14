package com.shiroecreative.todolist.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.module.task.TaskActivity;

import static com.shiroecreative.todolist.module.Constants.TASK_TYPE;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private FloatingActionButton btnTaskAdd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_home, container, false);

        btnTaskAdd = fragmentView.findViewById(R.id.task_add_button);
        btnTaskAdd.setOnClickListener(view -> presenter.addTask());

        setTitle(getResources().getString(R.string.app_name));

        return fragmentView;
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sendToAddTask() {
        Intent intent = new Intent(activity, TaskActivity.class);
        intent.putExtra(TASK_TYPE, "ADD");
        startActivity(intent);
    }

    @Override
    public void sendToEditTask() {
        Intent intent = new Intent(activity, TaskActivity.class);
        intent.putExtra(TASK_TYPE, "EDIT");
        startActivity(intent);
    }
}
