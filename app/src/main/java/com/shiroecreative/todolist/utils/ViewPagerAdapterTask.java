package com.shiroecreative.todolist.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.shiroecreative.todolist.module.home.HomeContract;
import com.shiroecreative.todolist.module.home.TaskFragment;

public class ViewPagerAdapterTask extends FragmentStateAdapter {

    private RecyclerViewAdapterTodoList[] adapterTodoList;
    private HomeContract.Presenter presenter;

    public ViewPagerAdapterTask(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        final TaskFragment taskFragment = new TaskFragment(adapterTodoList[position]);
        taskFragment.setPresenter(presenter);
        return taskFragment;
    }

    @Override
    public int getItemCount() {
        // Only 2 Pages - Available and Completed
        return 2;
    }

    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setAdapterTodoList(RecyclerViewAdapterTodoList[] adapterTodoList) {
        this.adapterTodoList = adapterTodoList;
    }
}
