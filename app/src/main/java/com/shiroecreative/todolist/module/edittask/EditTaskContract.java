package com.shiroecreative.todolist.module.edittask;

import com.shiroecreative.todolist.base.BasePresenter;
import com.shiroecreative.todolist.base.BaseView;
import com.shiroecreative.todolist.data.model.Task;

public interface EditTaskContract {
    interface View extends BaseView<Presenter> {
        void redirectToTaskList();

        void showData(Task task);
    }

    interface Presenter extends BasePresenter {
        void saveData(Task task);

        void loadData(String id);

        void setId(String id);

        void deleteData();
    }
}
