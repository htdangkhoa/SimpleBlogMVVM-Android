package com.github.htdangkhoa.blogmvvmandroid.viewmodel;

import com.github.htdangkhoa.blogmvvmandroid.model.Post;
import com.github.htdangkhoa.blogmvvmandroid.util.Observable;

/**
 * Created by dangkhoa on 4/13/18.
 */

public class ItemPostViewModel extends Observable {
    Post post;

    public ItemPostViewModel(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getTitle() {
        return post.getTitle();
    }

    public String getBody() {
        return post.getBody();
    }

    public String get_id() {
        return post.get_id();
    }
}
