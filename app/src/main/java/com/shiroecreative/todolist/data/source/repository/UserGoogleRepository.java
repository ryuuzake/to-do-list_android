package com.shiroecreative.todolist.data.source.repository;

import android.content.Context;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.shiroecreative.todolist.R;
import com.shiroecreative.todolist.data.model.User;
import com.shiroecreative.todolist.utils.RequestResponseListener;

public class UserGoogleRepository {
    private final Context context;
    private final GoogleSignInClient googleSignInClient;

    public UserGoogleRepository(Context context) {
        this.context = context;
        // Request only the user's ID token, which can be used to identify the
        // user securely to your backend. This will contain the user's basic
        // profile (name, profile picture URL, etc) so you should not need to
        // make an additional call to personalize your application.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.server_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public User getUser() {
        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        // Need a slim down version
        if (account != null) {
            final User user = new User();
            user.setEmail(account.getEmail());
            return user;
        }
        return null;
    }

    public Intent getSignInIntent() {
        return googleSignInClient.getSignInIntent();
    }

    public void handleSignInResult(Intent intent, RequestResponseListener<GoogleSignInAccount> listener) {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(intent);
            listener.onSuccess(task.getResult(ApiException.class));
        } catch (ApiException e) {
            listener.onError(e.getMessage());
        }
    }
}
