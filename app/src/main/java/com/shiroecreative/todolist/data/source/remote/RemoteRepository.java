package com.shiroecreative.todolist.data.source.remote;

import android.text.TextUtils;

import com.shiroecreative.todolist.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class RemoteRepository<T> {
    protected T endpoint;

    public RemoteRepository(Class<T> endpoint) {
        this(endpoint, null);
    }

    public RemoteRepository(Class<T> endpoint, String token) {
        this.endpoint = getRetrofit(token).create(endpoint);
    }

    private Retrofit getRetrofit(String token) {
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create());

        @SuppressWarnings("deprecation") final HttpLoggingInterceptor level = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .addInterceptor(level);

        if (!TextUtils.isEmpty(token)) {
            JWTAuthenticationInterceptor interceptor =
                    new JWTAuthenticationInterceptor(token);
            httpClient.addInterceptor(interceptor);
        }
        return builder.client(httpClient.build()).build();
    }

    protected String getBaseUrl() {
        return BuildConfig.baseUrl;
    }
}
