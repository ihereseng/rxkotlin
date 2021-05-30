package com.example.rxkotlin

import android.annotation.SuppressLint
import com.example.rxkotlin.api.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

object MainPresenter : IMainPresenter.Presenter {

    lateinit var view: MainActivity

    @SuppressLint("CheckResult")
    override fun getUser() {
        RetrofitBuilder.buildService().getUser("50")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = {
                    it.results?.let { results ->
                        view.onUserLoaded(results)
                    }
                },
                onError = {
                    view.onUserErrorLoaded("Something bad happen please try again later")
                }
            )
    }


}