package com.shiroecreative.todolist.module.edittask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.data.model.Task;

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    TextInputEditText tietTaskName;
    TextInputEditText tietTaskDescription;
    private TextInputEditText tietTaskDate;
    private CheckBox cbChecked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_edit_task, container, false);

        final TextView taskTitle = fragmentView.findViewById(R.id.tv_task_title);
        final Button btnEditTask = fragmentView.findViewById(R.id.btn_edit_task);
        final Button btnDeleteTask = fragmentView.findViewById(R.id.btn_delete_task);
        tietTaskName = fragmentView.findViewById(R.id.tiet_task_name);
        tietTaskDescription = fragmentView.findViewById(R.id.tiet_task_description);
        tietTaskDate = fragmentView.findViewById(R.id.tiet_task_date);
        cbChecked = fragmentView.findViewById(R.id.cb_checked);

        setTitle(getResources().getString(R.string.task_title_edit));
        taskTitle.setText(R.string.task_title_edit);
        btnEditTask.setVisibility(View.VISIBLE);
        btnEditTask.setOnClickListener(v -> this.setBtnEditClick());
        btnDeleteTask.setOnClickListener(v -> this.setBtnDeleteClick());
        btnDeleteTask.setVisibility(View.VISIBLE);

        presenter.start();

        return fragmentView;
    }

    private void setBtnEditClick() {
        String name = tietTaskName.getText().toString();
        String description = tietTaskDescription.getText().toString();
        String date = tietTaskDate.getText().toString();
        Boolean checked = cbChecked.isChecked();
        presenter.saveData(new Task(null, name, description, date, checked));
    }

    private void setBtnDeleteClick() {
        presenter.deleteData();
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        activity.finish();
    }

    @Override
    public void showData(Task task) {
        tietTaskName.setText(task.getTitle());
        tietTaskDescription.setText(task.getDescription());
        tietTaskDate.setText(task.getDate());
        cbChecked.setChecked(task.getChecked());
    }
}
