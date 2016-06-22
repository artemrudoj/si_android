package com.wintersportcoaches.common.rest.service;

import com.wintersportcoaches.common.Chat;
import com.wintersportcoaches.common.model.Lesson;
import com.wintersportcoaches.common.model.Message;
import com.wintersportcoaches.common.rest.LoginResponseSerializer;
import com.wintersportcoaches.common.user.BaseUser;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by artem on 23.05.16.
 */
public interface NetworkService {
    @FormUrlEncoded
    @POST("/api/user/login/")
    Call<LoginResponseSerializer> user_login(@Field("phone") String phone,
                                             @Field("password") String password);


    @Multipart
    @POST("/api/user/register/")
    Call<LoginResponseSerializer> user_register(@Part("phone") RequestBody phone,
                               @Part("password") RequestBody password,
                               @Part("first_name") RequestBody first_name,
                               @Part("last_name") RequestBody last_name,
                               @Part("email") RequestBody email,
                               @Part("is_couch") Boolean isCoach,
                               @Part MultipartBody.Part photo);


    @POST("/api/user/users_get/")
    Call<List<BaseUser>> users_get();




    @FormUrlEncoded
    @POST("/api/training/get_list/")
    Call<List<Lesson>> lesson_get(@Field("hash") String hash);

    @FormUrlEncoded
    @POST("/api/training/get_my_list/")
    Call<List<Lesson>> lesson_get_by_statuses(@Field("hash") String hash,
                                              @Field("statuses") List<Integer> statuses,
                                              @Field("i_am_as") String userType);


    @FormUrlEncoded
    @POST("/api/user/get/")
    Call<BaseUser> user_get(@Field("id") int id);

    @FormUrlEncoded
    @POST("/api/chat/get_my_list/")
    Call<List<Chat>> chats_get(@Field("hash") String hash);

    @FormUrlEncoded
    @POST("/api/chat/get_chat_history/")
    Call<List<Message>> get_chat_history(@Field("hash") String hash, @Field("id") int id);


    @FormUrlEncoded
    @POST("/api/chat/get_with_user/")
    Call<Chat> get_chat_with_user(@Field("hash") String hash, @Field("id") int id);

    @FormUrlEncoded
    @POST("/api/training/create/")
    Call<Object> create_lesson(@Field("hash") String hash,
                               @Field("place_string") String placeString,
                               @Field("type") int type,
                               @Field("time_start") String time_start,
                               @Field("couch") String couch);



    @FormUrlEncoded
    @POST("/api/chat/send_message/")
    Call<Object> send_message(@Field("hash") String hash, @Field("text") String text, @Field("chat") int chatId);
}
