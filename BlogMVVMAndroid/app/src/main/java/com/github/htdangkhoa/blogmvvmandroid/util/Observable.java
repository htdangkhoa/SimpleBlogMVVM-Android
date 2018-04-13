package com.github.htdangkhoa.blogmvvmandroid.util;

import android.content.Context;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by dangkhoa on 4/13/18.
 */

public class Observable extends java.util.Observable {
    Context context;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Observable() {
    }

    public Observable(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }
}
