package com.example.rxkotlin

import com.example.rxkotlin.api.RetrofitBuilder
import com.example.rxkotlin.model.User
import io.reactivex.Observable

class MainPresenter: IMainPresenter.Presenter {

    override fun getUser(): Observable<User> {
        return RetrofitBuilder.buildService().getUser("50")
    }
}