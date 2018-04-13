package com.github.htdangkhoa.blogmvvmandroid.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.htdangkhoa.blogmvvmandroid.R;
import com.github.htdangkhoa.blogmvvmandroid.databinding.FragmentHomeBinding;
import com.github.htdangkhoa.blogmvvmandroid.view.adapter.HomeAdapter;
import com.github.htdangkhoa.blogmvvmandroid.view.widget.ItemTouchHelperCallback;
import com.github.htdangkhoa.blogmvvmandroid.viewmodel.HomeFragmentViewModel;

import java.util.Observable;
import java.util.Observer;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements Observer {
    Context context;

    FragmentHomeBinding binding;
    HomeFragmentViewModel model;

    RecyclerView recyclerView;
    FloatingActionButton btnAdd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = getContext();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,
                container, false);

        model = new HomeFragmentViewModel(context);
        binding.setHome(model);
        model.addObserver(this);

        recyclerView = binding.recyclerView;
        btnAdd = binding.btnAdd;

        init();

        return binding.getRoot();
    }

    private void init() {
        model.fetchPosts();

        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                RecyclerView.VERTICAL, false));

        HomeAdapter adapter = new HomeAdapter(model.getPosts());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 ||dy<0 && btnAdd.isShown()) btnAdd.hide();

                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    btnAdd.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        OverScrollDecoratorHelper.setUpOverScroll(recyclerView,
                OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        ItemTouchHelperCallback itemTouchHelperCallback = new ItemTouchHelperCallback(
                (viewHolder, swipeDirection) -> model.deletePost(adapter, viewHolder
                        .getAdapterPosition())
        );
        ItemTouchHelper helper = new ItemTouchHelper(itemTouchHelperCallback);
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void update(Observable o, Object arg) {
        HomeAdapter adapter = (HomeAdapter) recyclerView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.unSubscribeFromObservable();
    }
}
