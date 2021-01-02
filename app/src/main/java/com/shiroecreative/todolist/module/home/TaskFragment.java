package com.shiroecreative.todolist.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseView;
import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.utils.RecyclerViewAdapterTodoList;
import com.shiroecreative.todolist.utils.TaskUtil;

public class TaskFragment extends Fragment implements BaseView<HomeContract.Presenter> {

    private RecyclerViewAdapterTodoList adapterTodoList;
    private HomeContract.Presenter presenter;

    public TaskFragment() {
        super();
    }

    public TaskFragment(RecyclerViewAdapterTodoList adapterTodoList) {
        super();
        this.adapterTodoList = adapterTodoList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.task_recycler_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        createRecyclerView(view);
    }

    private void createRecyclerView(View view) {
        RecyclerView rvTask = view.findViewById(R.id.rv_task);
        rvTask.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
