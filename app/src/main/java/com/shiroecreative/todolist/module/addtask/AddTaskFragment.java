package com.shiroecreative.todolist.module.addtask;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.module.shared.DatePickerFragment;
import com.shiroecreative.todolist.utils.DateUtil;

public class AddTaskFragment extends BaseFragment<AddTaskActivity, AddTaskContract.Presenter> implements AddTaskContract.View, DatePickerDialog.OnDateSetListener {

    TextInputEditText tietTaskName;
    TextInputEditText tietTaskDescription;
    private TextInputEditText tietTaskDate;
    private CheckBox cbChecked;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_task, container, false);
        presenter.start();

        final TextView taskTitle = fragmentView.findViewById(R.id.tv_task_title);
        final Button btnTaskAdd = fragmentView.findViewById(R.id.btn_task_add);
        tietTaskName = fragmentView.findViewById(R.id.tiet_task_name);
        tietTaskDescription = fragmentView.findViewById(R.id.tiet_task_description);
        tietTaskDate = fragmentView.findViewById(R.id.tiet_task_date);
        final ImageButton btnDatePicker = fragmentView.findViewById(R.id.btn_date_picker);
        cbChecked = fragmentView.findViewById(R.id.cb_checked);

        setTitle(getResources().getString(R.string.task_title_add));
        taskTitle.setText(R.string.task_title_add);
        btnTaskAdd.setVisibility(View.VISIBLE);
        btnTaskAdd.setOnClickListener(view -> this.onClickSave());
        btnDatePicker.setOnClickListener(v -> new DatePickerFragment(this).show(getFragmentManager(), "datePicker"));

        return fragmentView;
    }

    private void onClickSave() {
        String name = tietTaskName.getText().toString();
        String description = tietTaskDescription.getText().toString();
        String date = tietTaskDate.getText().toString();
        Boolean checked = cbChecked.isChecked();
        presenter.saveData(new Task(null, name, description, date, checked));
    }

    @Override
    public void setPresenter(AddTaskContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
        activity.finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        tietTaskDate.setText(DateUtil.convertDateString(year, month, day));
    }
}
