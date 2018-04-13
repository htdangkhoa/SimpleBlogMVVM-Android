package com.github.htdangkhoa.blogmvvmandroid.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.github.htdangkhoa.blogmvvmandroid.App;
import com.github.htdangkhoa.blogmvvmandroid.model.Post;
import com.github.htdangkhoa.blogmvvmandroid.model.Response;
import com.github.htdangkhoa.blogmvvmandroid.util.Observable;
import com.github.htdangkhoa.blogmvvmandroid.util.Utils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dangkhoa on 4/13/18.
 */

public class PostFormFragmentViewModel extends Observable {
    String id, title, body;
    boolean mode = false;

    public PostFormFragmentViewModel(Context context) {
        super(context);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }

    public void onSubmit() {
        if (title == null || body == null || title.isEmpty() || body.isEmpty()) {
            Utils.showAlertDialog(getContext(), "Error", "Please enter all fields.",
                    null, "OK", null,
                    (dialog, which) -> dialog.dismiss()).show();

            return;
        }

        Disposable disposable;
        HashMap<String, Object> map = new HashMap<>();

        if (mode) {
            map.put("id", getId());
            map.put("title", getTitle());
            map.put("body", getBody());

            disposable = App.getIService().editPost(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onError);
        } else {
            map.put("title", getTitle());
            map.put("body", getBody());

            disposable = App.getIService().addPost(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSuccess, this::onError);
        }

        addDisposable(disposable);
    }

    private void onSuccess(Response<Post> postResponse) {
        Post post = postResponse.getData();
        Log.i("TITLE", post.getTitle());
        Log.i("BODY", post.getBody());

        Utils.Fragment.popBackStack(getContext());
    }

    private void onError (Throwable throwable) {
    }
}
