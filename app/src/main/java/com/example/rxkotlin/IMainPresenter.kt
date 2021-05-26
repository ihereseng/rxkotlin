package com.example.rxkotlin

import com.example.rxkotlin.model.Result
import io.reactivex.Observable

interface IMainPresenter {
    interface Presenter {
        fun getUser() : Observable<Result>
    }

    interface View {
        fun setUserToTable()
    }
}