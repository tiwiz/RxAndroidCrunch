package it.tiwiz.rxjavacrunch.part8;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class Part8Activity extends AppCompatActivity implements OnObservableRetrievedListener {

    private CompositeSubscription subscriptions;
    private RecyclerView logRecyclerView;
    private LogViewManager logViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part8);
        logRecyclerView = (RecyclerView) findViewById(R.id.logRecyclerView);
        logViewManager = new LogViewManager(logRecyclerView);
        Part8WorkerFragment.getFrom(getSupportFragmentManager());
    }

    @Override
    public void onObservableRetrieved(@NonNull Observable<Integer> observable, @Subjects.Type String type) {
        subscriptions.add(observable.doOnSubscribe(Logger.doOnSubscribe(logViewManager, type))
                .doOnUnsubscribe(Logger.doOnUnsubscribe(logViewManager, type))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(LoggerObserver.from(logViewManager, type)));
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
