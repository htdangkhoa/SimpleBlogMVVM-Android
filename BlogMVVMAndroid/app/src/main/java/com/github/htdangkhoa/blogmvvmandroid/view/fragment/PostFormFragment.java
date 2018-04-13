package com.github.htdangkhoa.blogmvvmandroid.view.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.htdangkhoa.blogmvvmandroid.R;
import com.github.htdangkhoa.blogmvvmandroid.databinding.FragmentPostFormBinding;
import com.github.htdangkhoa.blogmvvmandroid.viewmodel.PostFormFragmentViewModel;

import java.util.Observable;
import java.util.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostFormFragment extends Fragment implements Observer {
    Context context;

    FragmentPostFormBinding binding;
    PostFormFragmentViewModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.context = getContext();

        Bundle bundle = getArguments();

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post_form,
                container, false);

        model = new PostFormFragmentViewModel(context);

        if (bundle != null) {
            String id = bundle.getString("ID");
            String title = bundle.getString("TITLE");
            String body = bundle.getString("BODY");
            boolean isEdit = bundle.getBoolean("IS_EDIT");

            model.setId(id);
            model.setTitle(title);
            model.setBody(body);
            model.setMode(isEdit);
        }

        model.addObserver(this);

        binding.setForm(model);

        return binding.getRoot();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.unSubscribeFromObservable();
    }
}
