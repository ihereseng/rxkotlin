package com.example.rxkotlin

import com.example.rxkotlin.api.RetrofitBuilder
import com.example.rxkotlin.model.Result
import io.reactivex.Observable

class MainPresenter: IMainPresenter.Presenter {

    override fun getUser(): Observable<Result> {
        return RetrofitBuilder.buildService().getUser("2")
    }
}