package com.wintersportcoaches.common.rest.service;

import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by artem on 23.05.16.
 */
public interface NetworkService {
    @FormUrlEncoded
    @POST("/api/user/login/")
    Call<String> user_login(@Field("phone") String phone,
                            @Field("password") String password);


    @POST("/api/user/users_get/")
    Call<List<BaseUser>> users_get();


}
