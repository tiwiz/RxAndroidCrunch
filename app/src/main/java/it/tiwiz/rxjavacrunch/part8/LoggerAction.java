package it.tiwiz.rxjavacrunch.part8;

import android.util.Log;

import rx.functions.Action0;

import static it.tiwiz.rxjavacrunch.part8.OnObservableRetrievedListener.Subjects;

public class LoggerAction implements Action0 {

    private final String TAG;

    public static Action0 from(@Subjects String type) {
        return new LoggerAction(type);
    }

    private LoggerAction(String type) {
        this.TAG = TagManager.from(type);
    }

    @Override
    public void call() {
        Log.d(TAG, "Subscribed to the Observable");
    }
}
