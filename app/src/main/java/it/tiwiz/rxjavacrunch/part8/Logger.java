package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.util.Log;

import rx.functions.Action0;

public class Logger implements Action0 {

    private final String TAG;
    private final String message;
    private final LogViewManager logViewManager;
    @Subjects.Type
    private final String type;

    public static Action0 doOnSubscribe(@NonNull LogViewManager manager, @Subjects.Type String type) {
        return new Logger(manager, type, "Subscribed to the Observable");
    }

    public static Action0 doOnUnsubscribe(@NonNull LogViewManager manager, @Subjects.Type String type) {
        return new Logger(manager, type, "Unsubscribed from the Observable");
    }

    private Logger(@NonNull LogViewManager manager, @Subjects.Type String type, @NonNull String message) {
        this.logViewManager = manager;
        this.TAG = TagManager.from(type);
        this.message = message;
        this.type = type;
    }

    @Override
    public void call() {
        Log.d(TAG, message);
        logViewManager.onNewMessage(message, type);
    }
}
