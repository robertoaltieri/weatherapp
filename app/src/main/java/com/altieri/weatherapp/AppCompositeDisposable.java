package com.altieri.weatherapp;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;

public class AppCompositeDisposable implements Disposable, DisposableContainer {
    private CompositeDisposable mCompositeDisposable;

    @Inject
    public AppCompositeDisposable(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void dispose() {
        mCompositeDisposable.dispose();
    }

    @Override
    public boolean isDisposed() {
        return mCompositeDisposable.isDisposed();
    }

    @Override
    public boolean add(Disposable d) {
        return mCompositeDisposable.add(d);
    }

    @Override
    public boolean remove(Disposable d) {
        return mCompositeDisposable.remove(d);
    }

    @Override
    public boolean delete(Disposable d) {
        return mCompositeDisposable.delete(d);
    }

    public void reset() {
        mCompositeDisposable = new CompositeDisposable();
    }
}
