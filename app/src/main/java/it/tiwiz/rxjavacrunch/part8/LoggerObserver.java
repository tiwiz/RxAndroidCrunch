package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.util.Log;

import rx.Observer;


public class LoggerObserver implements Observer<Integer> {
    private final String TAG;
    @Subjects.Type
    private final String type;
    private final LogViewManager manager;

    public static Observer<Integer> from(@NonNull final LogViewManager manager, @NonNull String type) {
        return new LoggerObserver(manager, type);
    }

    private LoggerObserver(@NonNull LogViewManager manager, @Subjects.Type String type) {
        this.type = type;
        this.TAG = TagManager.from(type);
        this.manager = manager;
    }

    @Override
    public void onCompleted() {
        final String message = "onCompleted()";
        Log.d(TAG, message);
        saveOnUi(message);
    }

    @Override
    public void onError(Throwable e) {
        final String message = "onError: " + e.getMessage();
        Log.d(TAG, message);
        saveOnUi(message);
    }

    private void saveOnUi(String message) {
        manager.onNewMessage(message, type);
    }

    @Override
    public void onNext(Integer integer) {
        final String message = "onNext: " + integer;
        Log.d(TAG, message);
        saveOnUi(message);
    }
}
