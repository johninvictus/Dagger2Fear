package com.invictus.dagger2fear.dagger2fear.repository.api;

import com.invictus.dagger2fear.dagger2fear.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by invictus on 4/28/18.
 */

public class AuthInterceptor implements Interceptor {
    public AuthInterceptor(){}

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
