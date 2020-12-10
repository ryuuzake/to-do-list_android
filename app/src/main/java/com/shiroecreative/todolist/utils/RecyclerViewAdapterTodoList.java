package com.shiroecreative.todolist.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.data.model.Task;

import java.util.List;

public class RecyclerViewAdapterTodoList extends RecyclerView.Adapter<RecyclerViewAdapterTodoList.ViewHolder> {

    private List<Task> taskList;
    private TodoListClickListener todoListClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.task = taskList.get(position);
        holder.tvTaskName.setText(holder.task.getTitle());
        holder.tvTaskTime.setText(holder.task.getDescription());
        holder.itemView.setOnClickListener(view -> todoListClickListener.onTaskClick(holder.task));
        holder.cbTask.setOnClickListener(view -> todoListClickListener.onTaskCheckBoxClick(holder.task));
    }

    @Override
    public int getItemCount() {
        return (taskList == null) ? 0 : taskList.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public void setTodoListClickListener(TodoListClickListener todoListClickListener) {
        this.todoListClickListener = todoListClickListener;
    }

    public interface TodoListClickListener {
        void onTaskClick(Task task);

        void onTaskCheckBoxClick(Task task);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final View view;
        final TextView tvTaskName;
        final TextView tvTaskTime;
        final CheckBox cbTask;
        Task task;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            tvTaskName = itemView.findViewById(R.id.tv_task_name);
            tvTaskTime = itemView.findViewById(R.id.tv_task_time);
            cbTask = itemView.findViewById(R.id.cb_task);
        }
    }
}
