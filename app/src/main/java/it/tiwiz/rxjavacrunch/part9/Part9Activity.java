package it.tiwiz.rxjavacrunch.part9;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class Part9Activity extends AppCompatActivity {

    private TextView currentValue;

    @Nullable
    private Observable<String> observable;

    @Nullable
    private Subscription subscription;

    @NonNull
    private static Observable<String> observableFactory() {
        return Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .take(20)
                .map(String::valueOf);
    }

    @NonNull
    private static Observable<String> createCachedObservable() {
        return observableFactory()
                .observeOn(AndroidSchedulers.mainThread())
                .replay(1)
                .autoConnect();
    }

    @NonNull
    private Observable<String> getOrCreateObservable() {
        Observable<String> observable = this.observable;
        if (observable == null) {
            observable = createCachedObservable();
            this.observable = observable;
        }
        return observable;
    }

    private void resetObservable() {
        this.observable = null;
        unsubscribe();
    }

    private void unsubscribe() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    private void subscribe() {
        if (subscription == null || subscription.isUnsubscribed()) {
            subscription = getOrCreateObservable().subscribe(new Subscriber<String>() {
                @Override
                public void onNext(String s) {
                    currentValue.setText(s);
                }

                @Override
                public void onCompleted() {
                    onTerminationNotification("COMPLETED");
                }

                @Override
                public void onError(Throwable e) {
                    onTerminationNotification("ERROR");
                }
            });
        }
    }

    private void onTerminationNotification(@NonNull String message) {
        resetObservable();

        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("RESTART", v -> subscribe())
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part9);

        currentValue = (TextView) findViewById(R.id.currentValue);
        observable = getLastCustomNonConfigurationInstance();
        subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return observable;
    }

    @Override
    public Observable<String> getLastCustomNonConfigurationInstance() {
        return (Observable<String>) super.getLastCustomNonConfigurationInstance();
    }
}
