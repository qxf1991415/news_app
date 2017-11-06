package com.qxf.newsapp.data;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2017/11/6.
 */

public abstract class RxUseCase<Q, P> {

    protected abstract Observable<P> buildUseCaseObservable(Q requestValues);

    public Observable<P> executeUseCase() {
        return executeUseCase(null);
    }

    public Observable<P> executeUseCase(Q requestValues) {
        return buildUseCaseObservable(requestValues);
    }

    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }

    /**
     * Data received from a request.
     */
    public interface ResponseValue {
    }
}
