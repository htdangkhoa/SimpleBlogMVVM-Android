package com.github.htdangkhoa.blogmvvmandroid.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.htdangkhoa.blogmvvmandroid.R;
import com.github.htdangkhoa.blogmvvmandroid.databinding.ItemPostBinding;
import com.github.htdangkhoa.blogmvvmandroid.model.Post;
import com.github.htdangkhoa.blogmvvmandroid.util.Utils;
import com.github.htdangkhoa.blogmvvmandroid.view.fragment.PostFormFragment;
import com.github.htdangkhoa.blogmvvmandroid.viewmodel.ItemPostViewModel;

import java.util.List;

/**
 * Created by dangkhoa on 4/12/18.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Post> posts;

    public HomeAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        ItemPostBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_post,
                parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.bindPost(post);

        holder.binding.getRoot().setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("ID", post.get_id());
            bundle.putString("TITLE", post.getTitle());
            bundle.putString("BODY", post.getBody());
            bundle.putBoolean("IS_EDIT", true);

            Utils.Fragment.addFragment(context, new PostFormFragment(), true,
                    R.id.frameLayout, bundle, Utils.Fragment.Anim.FROM_BOTTOM_TO_TOP);
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void removeItem(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemPostBinding binding;

        public ViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindPost(Post post) {
            if (binding.getPost() == null) {
                binding.setPost(new ItemPostViewModel(post));
            } else {
                binding.getPost().setPost(post);
            }
        }
    }
}
