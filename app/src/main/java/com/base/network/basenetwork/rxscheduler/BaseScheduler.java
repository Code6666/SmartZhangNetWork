package com.base.network.basenetwork.rxscheduler;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

/**
 * Created by d on 2018/9/25.
 * desc:RxJava2 基础相应类型
 */

public class BaseScheduler<T> implements
        ObservableTransformer<T, T>,
        SingleTransformer<T, T>,
        MaybeTransformer<T, T>,
        CompletableTransformer,
        FlowableTransformer<T, T> {

    private Scheduler subscribeOnScheduler; // 上游线程
    private Scheduler observeOnScheduler;   // 下游线程

    public BaseScheduler(Scheduler subscribeOnScheduler,
                         Scheduler observeOnScheduler) {
        this.subscribeOnScheduler = subscribeOnScheduler;
        this.observeOnScheduler = observeOnScheduler;
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }

    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }

    @Override
    public MaybeSource<T> apply(Maybe<T> upstream) {
        return upstream
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }

    @Override
    public CompletableSource apply(Completable upstream) {
        return upstream
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }

    @Override
    public Publisher<T> apply(Flowable<T> upstream) {
        return upstream
                .subscribeOn(subscribeOnScheduler)
                .observeOn(observeOnScheduler);
    }


}