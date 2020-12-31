package com.shiroecreative.todolist.data.source.repository;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.data.request_response.LoginRequest;
import com.shiroecreative.todolist.data.request_response.RegisterRequest;
import com.shiroecreative.todolist.data.source.remote.UserEndpoint;
import com.shiroecreative.todolist.data.source.remote.UserRemoteRepository;
import com.shiroecreative.todolist.data.source.session.UserSessionRepository;
import com.shiroecreative.todolist.utils.RequestResponseListener;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class UserRepositoryTest {

    private UserRepository repository;

    private String tEmail = "example@example.com";
    private final RequestResponseListener<User> listener = new RequestResponseListener<User>() {
        @Override
        public void onSuccess(User user) {
            assertEquals(tEmail, user.getEmail());
            assertNotNull(user.getToken());
            assertNull(user.getPassword());
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
    private String tPassword = "password";

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        repository = new UserRepositoryImpl(
                new UserSessionRepository(appContext),
                new UserRemoteRepository(UserEndpoint.class)
        );
    }

    @Test
    public void register() {
        String tEmail = "example1@example.com";

        repository.register(new RegisterRequest(tEmail, tEmail, tPassword, tPassword), listener);
    }

    @Test
    public void login() {
        repository.login(new LoginRequest(tEmail, tEmail, tPassword), listener);
    }
}