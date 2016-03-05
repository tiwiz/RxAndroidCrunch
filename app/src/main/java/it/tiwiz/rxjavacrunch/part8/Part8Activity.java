package it.tiwiz.rxjavacrunch.part8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public class Part8Activity extends AppCompatActivity implements OnObservableRetrievedListener {

    private CompositeSubscription subscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part8);
        Part8WorkerFragment.getFrom(getSupportFragmentManager());
    }

    @Override
    public void onObservableRetrieved(@NonNull Observable<Integer> observable, @Subjects String type) {
        subscriptions.add(observable.doOnSubscribe(LoggerAction.from(type))
                .subscribe(LoggerObserver.from(type)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscriptions = SubscriptionUtils.createFrom(subscriptions);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SubscriptionUtils.safeUnsubscribeFrom(subscriptions);
    }
}
