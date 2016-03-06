package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.util.Log;

import rx.functions.Action0;

import static it.tiwiz.rxjavacrunch.part8.OnObservableRetrievedListener.Subjects;

public class Logger implements Action0 {

    private final String TAG;
    private final String message;

    public static Action0 doOnSubscribe(@Subjects String type) {
        return new Logger(type, "Subscribed to the Observable");
    }

    public static Action0 doOnUnsubscribe(@Subjects String type) {
        return new Logger(type, "Unsubscribed from the Observable");
    }

    private Logger(String type, @NonNull String message) {
        this.TAG = TagManager.from(type);
        this.message = message;
    }

    @Override
    public void call() {
        Log.d(TAG, message);
    }
}
