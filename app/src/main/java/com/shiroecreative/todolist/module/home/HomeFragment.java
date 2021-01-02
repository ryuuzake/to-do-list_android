package com.shiroecreative.todolist.module.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.base.BaseFragment;
import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.module.addtask.AddTaskActivity;
import com.shiroecreative.todolist.module.edittask.EditTaskActivity;
import com.shiroecreative.todolist.module.login.LoginActivity;
import com.shiroecreative.todolist.utils.RecyclerViewAdapterTodoList;
import com.shiroecreative.todolist.utils.TaskUtil;
import com.shiroecreative.todolist.utils.ViewPagerAdapterTask;

import java.util.List;

import static com.shiroecreative.todolist.utils.Constants.TASK_ID;

public class HomeFragment extends BaseFragment<HomeActivity, HomeContract.Presenter> implements HomeContract.View {

    private RecyclerViewAdapterTodoList[] adapterTodoList;
    private int viewPagerPosition = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_home, container, false);
        return fragmentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        FloatingActionButton btnTaskAdd = fragmentView.findViewById(R.id.btn_task_add);
        btnTaskAdd.setOnClickListener(v -> presenter.addTask());
        activity.btnLogout.setOnClickListener(v -> presenter.logout());
        adapterTodoList = new RecyclerViewAdapterTodoList[]{
                new RecyclerViewAdapterTodoList(),
                new RecyclerViewAdapterTodoList()
        };
        createSearchBar();
        createViewPager();
        presenter.getTasks();

        setTitle(getResources().getString(R.string.app_name));
    }

    private void createViewPager() {
        ViewPager2 viewPager = fragmentView.findViewById(R.id.pager);
        TabLayout tabLayout = fragmentView.findViewById(R.id.tab_layout);

        final ViewPagerAdapterTask viewPagerAdapterTask = new ViewPagerAdapterTask(this);
        viewPagerAdapterTask.setAdapterTodoList(adapterTodoList);
        viewPagerAdapterTask.setPresenter(presenter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewPagerPosition = position;
            }
        });
        viewPager.setAdapter(viewPagerAdapterTask);

        String[] tabTitles = activity.getResources().getStringArray(R.array.tab_titles);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();
    }

    private void createSearchBar() {
        EditText etSearch = fragmentView.findViewById(R.id.task_search_editText);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapterTodoList[viewPagerPosition].getFilter().filter(editable.toString());
            }
        });
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
        // Hardcode setTaskList
        adapterTodoList[0].setTaskList(TaskUtil.filterAvailable(tasks));
        adapterTodoList[1].setTaskList(TaskUtil.filterCompleted(tasks));
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

    @Override
    public void sendToLogin() {
        startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }
}
