package com.example.rxkotlin

import com.example.rxkotlin.model.User
import io.reactivex.Observable

interface IMainPresenter {
    interface Presenter {
        fun getUser() : Observable<User>
    }

    interface View {
        fun setUserToTable()
    }
}