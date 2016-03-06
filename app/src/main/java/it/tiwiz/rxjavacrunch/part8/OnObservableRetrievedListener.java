package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;

import rx.Observable;

public interface OnObservableRetrievedListener {
    void onObservableRetrieved(@NonNull Observable<Integer> observable, @Subjects.Type String type);
}
