package com.example.rxkotlin

import com.example.rxkotlin.api.ApiInterface
import com.example.rxkotlin.model.User
import io.reactivex.Observable

class MainPresenter: IMainPresenter.Presenter {

    override fun getUser(): Observable<List<User>> {
        return ApiInterface.create().getUser("2")
    }
}