package com.github.htdangkhoa.blogmvvmandroid.model;

/**
 * Created by dangkhoa on 4/12/18.
 */

public class Post {
    String _id, title, body;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
}
