package com.shiroecreative.todolist.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.module.addtask.AddTaskActivity;
import com.shiroecreative.todolist.module.edittask.EditTaskActivity;
import com.shiroecreative.todolist.utils.RecyclerViewAdapterTodoList;
import com.shiroecreative.todolist.utils.TaskUtil;

import java.util.List;

import static com.shiroecreative.todolist.utils.Constants.TASK_ID;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private FloatingActionButton btnTaskAdd;
    private RecyclerView rvTask;
    private RecyclerViewAdapterTodoList adapterTodoList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_home, container, false);

        rvTask = fragmentView.findViewById(R.id.rv_task);
        btnTaskAdd = fragmentView.findViewById(R.id.btn_task_add);
        btnTaskAdd.setOnClickListener(view -> presenter.addTask());
        adapterTodoList = new RecyclerViewAdapterTodoList();
        rvTask.setLayoutManager(new LinearLayoutManager(activity));
        rvTask.setAdapter(adapterTodoList);
        adapterTodoList.setTodoListClickListener(new RecyclerViewAdapterTodoList.TodoListClickListener() {
            @Override
            public void onTaskClick(Task task) {
                String id = task.getId();
                presenter.editTask(id);
            }

            @Override
            public void onTaskCheckBoxClick(Task task) {
                presenter.checkedTask(task);
            }

            @Override
            public boolean onTaskLongClick(Task task) {
                final String sharedText = TaskUtil.processForSharing(task);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharedText);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            }
        });
        presenter.getTasks();

        setTitle(getResources().getString(R.string.app_name));

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getTasks();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTasks(List<Task> tasks) {
        adapterTodoList.setTaskList(tasks);
    }

    @Override
    public void sendToAddTask() {
        Intent intent = new Intent(activity, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void sendToEditTask(String id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra(TASK_ID, id);
        startActivity(intent);
    }
}
