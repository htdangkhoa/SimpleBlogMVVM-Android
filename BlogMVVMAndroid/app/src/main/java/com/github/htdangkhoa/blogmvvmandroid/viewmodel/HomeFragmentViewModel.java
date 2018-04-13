package com.github.htdangkhoa.blogmvvmandroid.viewmodel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.htdangkhoa.blogmvvmandroid.App;
import com.github.htdangkhoa.blogmvvmandroid.R;
import com.github.htdangkhoa.blogmvvmandroid.model.Post;
import com.github.htdangkhoa.blogmvvmandroid.model.Response;
import com.github.htdangkhoa.blogmvvmandroid.network.IService;
import com.github.htdangkhoa.blogmvvmandroid.util.Observable;
import com.github.htdangkhoa.blogmvvmandroid.util.Utils;
import com.github.htdangkhoa.blogmvvmandroid.view.adapter.HomeAdapter;
import com.github.htdangkhoa.blogmvvmandroid.view.fragment.HomeFragment;
import com.github.htdangkhoa.blogmvvmandroid.view.fragment.PostFormFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dangkhoa on 4/12/18.
 */

public class HomeFragmentViewModel extends Observable {
    List<Post> posts = new ArrayList<>();

    public HomeFragmentViewModel(Context context) {
        super(context);
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void fetchPosts() {
        Disposable disposable = App.getIService().fetchPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);

        addDisposable(disposable);
    }

    private void onSuccess(Response<List<Post>> listResponse) {
        updateList(listResponse.getData());
    }

    private void onError(Throwable throwable) {
    }

    public void deletePost(HomeAdapter adapter, int position) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", posts.get(position).get_id());

        Disposable disposable = App.getIService().deletePost(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        adapter.removeItem(position);

        addDisposable(disposable);
    }

    public void openAddPostView() {
        Utils.Fragment.addFragment(getContext(), new PostFormFragment(), true,
                R.id.frameLayout, Bundle.EMPTY, Utils.Fragment.Anim.FROM_BOTTOM_TO_TOP);
    }

    private void updateList(List<Post> posts) {
        this.posts.addAll(posts);
        notifyObservers();
    }
}
