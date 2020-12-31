package com.shiroecreative.todolist.data.source.repository;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.shiroecreative.todolist.data.model.Task;
import com.shiroecreative.todolist.data.source.local.TaskTableHandler;
import com.shiroecreative.todolist.data.source.remote.TaskRemoteRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskRepositoryTest {

    private TaskRepository repository;

    private String tId = "1";
    private String tNextId = "2";
    private String tTitle = "Testing";
    private String tDescription = "Testing Description";
    private String tDate = "2020-12-31";
    private Boolean tChecked = false;

    private RequestResponseListener<Task> listener = new RequestResponseListener<Task>() {
        @Override
        public void onSuccess(Task task) {
            assertEquals(tTitle, task.getTitle());
            assertEquals(tDescription, task.getDescription());
            assertNotNull(task.getChecked());
            assertNotNull(task.getChecked());
        }

        @Override
        public void onEmpty() {
            fail();
        }

        @Override
        public void onError(String errorMessage) {
            fail();
        }
    };

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        // Prepare login token
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo0LCJ1c2VybmFtZSI6ImV4YW1wbGVAZXhhbXBsZS5jb20iLCJleHAiOjE2MDk0MjY4MDEsImVtYWlsIjoiZXhhbXBsZUBleGFtcGxlLmNvbSJ9.ZahWc6K3rqjXPrXIIMqlcejNOWsxj4L5b0KhLMWphQk";
        repository = new TaskRepositoryImpl(
                new TaskTableHandler(appContext),
                new TaskRemoteRepository(token)
        );
    }

    @Test
    public void createTask_fillRequiredField() {
        repository.createTask(new Task(tId, tTitle, tDescription), listener);
    }

    @Test
    public void createTask_fillAllField() {
        String tId = tNextId;
        String tTitle = this.tTitle + tId;
        repository.createTask(new Task(tId, tTitle, tDescription, tDate, tChecked), listener);
    }

    @Test
    public void updateTask() {
        String tTitle = this.tTitle + "1";
        repository.updateTask(new Task(tId, tTitle, tDescription, tDate, tChecked), listener);
    }

    @Test
    public void readAllTask() {
        repository.readAllTask(new RequestResponseListener<List<Task>>() {
            @Override
            public void onSuccess(List<Task> tasks) {
                assertEquals(2, tasks.size());
            }

            @Override
            public void onEmpty() {
                fail();
            }

            @Override
            public void onError(String errorMessage) {
                fail();
            }
        });
    }

    @Test
    public void readTaskById() {
        repository.readTaskById(tId, listener);
    }

    @Test
    public void deleteTask() {
        repository.deleteTask(tId, listener);
        repository.deleteTask(tNextId, listener);
    }
}