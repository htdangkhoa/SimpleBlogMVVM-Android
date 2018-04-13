package com.github.htdangkhoa.blogmvvmandroid.model;

import java.util.List;

/**
 * Created by dangkhoa on 4/12/18.
 */

public class Response<T> {
    T data;
    int code;

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }
}
