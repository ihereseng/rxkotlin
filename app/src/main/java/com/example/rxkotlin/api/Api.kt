package com.example.rxkotlin.api

import com.example.rxkotlin.model.Result
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface Api {
    @GET("api/")
    fun getUser(
        @Query("results") input: String
    ) : Observable<Result>
}