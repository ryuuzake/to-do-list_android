package com.shiroecreative.todolist.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.data.model.Task;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterTodoList extends RecyclerView.Adapter<RecyclerViewAdapterTodoList.ViewHolder>
        implements Filterable {

    private List<Task> taskList;
    private List<Task> taskListFiltered;
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
        holder.task = taskListFiltered.get(position);
        holder.tvTaskName.setText(holder.task.getTitle());
        holder.tvTaskTime.setText(holder.task.getDescription());
        holder.cbTask.setChecked(holder.task.getChecked());
        holder.itemView.setOnClickListener(view -> todoListClickListener.onTaskClick(holder.task));
        holder.cbTask.setOnClickListener(view -> todoListClickListener.onTaskCheckBoxClick(holder.task));
        holder.itemView.setOnLongClickListener(view -> todoListClickListener.onTaskLongClick(holder.task));
    }

    @Override
    public int getItemCount() {
        return (taskListFiltered == null) ? 0 : taskListFiltered.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.taskListFiltered = taskList;
        notifyDataSetChanged();
    }

    public void setTodoListClickListener(TodoListClickListener todoListClickListener) {
        this.todoListClickListener = todoListClickListener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final String string = charSequence.toString();
                final FilterResults filterResults = new FilterResults();
                List<Task> filteredList = new ArrayList<>();

                if (string.isEmpty()) {
                    filteredList = taskList;
                } else {
                    for (Task task : taskList) {
                        // Lookup for same title name
                        if (task.getTitle().toLowerCase().contains(string.toLowerCase())) {
                            filteredList.add(task);
                        }
                    }
                }
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                taskListFiltered = (List<Task>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface TodoListClickListener {
        void onTaskClick(Task task);

        void onTaskCheckBoxClick(Task task);

        boolean onTaskLongClick(Task task);
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
