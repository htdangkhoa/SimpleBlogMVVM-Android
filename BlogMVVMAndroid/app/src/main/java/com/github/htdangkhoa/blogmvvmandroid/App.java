package com.github.htdangkhoa.blogmvvmandroid;

import android.app.Application;

import com.github.htdangkhoa.blogmvvmandroid.network.IService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dangkhoa on 4/10/18.
 */

public class App extends Application {
    public static final String BASE_URL = "http://192.168.0.46:8888/";
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        if (retrofit == null)
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
    }

    public static final IService getIService() {
        return retrofit.create(IService.class);
    }
}
