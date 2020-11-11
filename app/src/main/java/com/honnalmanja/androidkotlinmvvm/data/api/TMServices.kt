package com.honnalmanja.androidkotlinmvvm.data.api

import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.AddTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.Tasks
import com.honnalmanja.androidkotlinmvvm.data.model.remote.task.UpdateTaskRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.CreateUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.LoginUserRequest
import com.honnalmanja.androidkotlinmvvm.data.model.remote.user.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface TMServices {


    //-------------------------- User Services ------------------------------//
    @POST("users/add")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest?): Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest?): Response<UserResponse>

    @POST("users/logoutAll")
    suspend fun logoutAll(@Header("Authorization") token: String?): Response<UserResponse>

    @POST("users/logout")
    suspend fun logoutUser(@Header("Authorization") token: String?): Response<UserResponse>
    //-------------------------- User Services ------------------------------//

    //-------------------------- User Services ------------------------------//
    //-------------------------- Task Services ------------------------------//
    @GET("tasks")
    fun getAllTasks(@Header("Authorization") token: String?):
            Response<List<Tasks>>

    @POST("tasks/add")
    fun addTask(@Header("Authorization") token: String?, @Body addTaskRequest: AddTaskRequest):
            Response<Tasks>

    @PATCH("tasks/{id}")
    fun updateTask(@Header("Authorization") token: String?,@Path("id") id: String?,
                   @Body updateTaskRequest: UpdateTaskRequest): Response<Tasks>

    @DELETE("tasks/{id}")
    fun deleteTask(@Header("Authorization") token: String?, @Path("id") id: String?):
            Response<Tasks>

    @GET("tasks/{id}")
    fun getATask(@Header("Authorization") token: String?, @Path("id") id: String?):
            Response<Tasks>

    //-------------------------- Task Services ------------------------------//
}