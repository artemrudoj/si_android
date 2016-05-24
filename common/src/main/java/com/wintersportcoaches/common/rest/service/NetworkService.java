package com.wintersportcoaches.common.rest.service;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.LoginResponseSerializer;
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
    Call<LoginResponseSerializer> user_login(@Field("phone") String phone,
                                             @Field("password") String password);


    @POST("/api/user/users_get/")
    Call<List<BaseUser>> users_get();

    @FormUrlEncoded
    @POST("/api/chat/get_my_list/")
    Call<List<Chat>> chats_get(@Field("hash") String hash);



    @FormUrlEncoded
    @POST("/api/chat/get_chat_history/")
    Call<List<Message>> get_chat_history(@Field("hash") String hash, @Field("id") String id);



    @FormUrlEncoded
    @POST("/api/chat/send_message/")
    Call<Object> send_message(@Field("hash") String hash, @Field("text") String text, @Field("chat") String chatId);
}
