package com.apzumi.challenge.common

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.applySchedulers(): Flowable<T> =
    subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())