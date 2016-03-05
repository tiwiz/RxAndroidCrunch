package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.util.Log;

import rx.Observer;

import static it.tiwiz.rxjavacrunch.part8.OnObservableRetrievedListener.*;


public class LoggerObserver implements Observer<Integer> {
    private final String TAG;

    public static Observer<Integer> from(@NonNull String type) {
        return new LoggerObserver(type);
    }

    private LoggerObserver(@Subjects String type) {
        this.TAG = TagManager.from(type);
    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "onCompleted()");
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: " + e.getMessage());
    }

    @Override
    public void onNext(Integer integer) {
        Log.d(TAG, "onNext: " + integer);
    }
}
