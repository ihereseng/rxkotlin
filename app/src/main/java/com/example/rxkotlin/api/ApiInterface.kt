package com.example.rxkotlin.api

import com.example.rxkotlin.model.User
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface ApiInterface {
    @GET("api/")
    fun getUser(
        @Query("results") input: String
    ) : Observable<List<User>>

    companion object {

        var BASE_URL = "https://randomuser.me/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}