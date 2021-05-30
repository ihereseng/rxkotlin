package com.example.rxkotlin

import com.example.rxkotlin.model.Result
import com.example.rxkotlin.model.User
import io.reactivex.Observable

interface IMainPresenter {
    interface Presenter {
        fun getUser()
    }

    interface View {
        fun getUser()

        fun onUserLoaded(results: List<Result>)

        fun onUserErrorLoaded(errorText: String)
    }
}