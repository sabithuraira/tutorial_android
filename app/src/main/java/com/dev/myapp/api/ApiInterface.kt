package com.dev.myapp.api

import com.dev.myapp.models.ResponseDeleteTodo
import com.dev.myapp.models.ResponseStoreTodo
import com.dev.myapp.models.ResponseTodo
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("todo")
    fun getTodos(): Call<ResponseTodo>

    @POST("todo")
    fun storeTodo(@Body params: RequestBody): Call<ResponseStoreTodo>

    @PATCH("todo/{id}")
    fun updateTodo(@Path("id") id: String,@Body params: RequestBody): Call<ResponseStoreTodo>

    @DELETE("todo/{id}")
    fun deleteToto(@Path("id") id: String): Call<ResponseDeleteTodo>
}