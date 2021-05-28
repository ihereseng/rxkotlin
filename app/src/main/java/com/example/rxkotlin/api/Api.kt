package com.example.rxkotlin.api

import com.example.rxkotlin.model.User
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface Api {
    @GET("api/")
    fun getUser(
        @Query("results") input: String
    ) : Observable<User>
}