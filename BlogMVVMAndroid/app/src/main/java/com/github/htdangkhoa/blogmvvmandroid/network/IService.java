package com.github.htdangkhoa.blogmvvmandroid.network;

import com.github.htdangkhoa.blogmvvmandroid.model.Post;
import com.github.htdangkhoa.blogmvvmandroid.model.Response;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by dangkhoa on 4/10/18.
 */

public interface IService {
    @GET("posts")
    Observable<Response<List<Post>>> fetchPosts();

    @POST("post")
    Observable<Response<Post>> addPost(@Body HashMap<String, Object> body);

    @PUT("post")
    Observable<Response<Post>> editPost(@Body HashMap<String, Object> body);

    @HTTP(method = "DELETE", path = "post", hasBody = true)
    Observable<Response<Post>> deletePost(@Body HashMap<String, Object> body);
}
