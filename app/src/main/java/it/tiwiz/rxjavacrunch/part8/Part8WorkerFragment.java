package it.tiwiz.rxjavacrunch.part8;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;
import rx.subscriptions.CompositeSubscription;

public class Part8WorkerFragment extends Fragment{

    private OnObservableRetrievedListener listener;

    private Subject<Integer, Integer> publishSubject = PublishSubject.create();
    private Subject<Integer, Integer> asyncSubject = AsyncSubject.create();
    private Subject<Integer, Integer> replaySubject = ReplaySubject.create();
    private Subject<Integer, Integer> behaviorSubject = BehaviorSubject.create();
    private CompositeSubscription subscriptions = new CompositeSubscription();

    private static final String TAG = "WorkerFragment";

    public static Fragment getFrom(@NonNull FragmentManager fragmentManager) {
        Fragment worker = fragmentManager.findFragmentByTag(TAG);
        if (worker == null) {
            worker = new Part8WorkerFragment();
            fragmentManager.beginTransaction().add(worker, TAG).commit();
        }

        return worker;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Observable<Integer> source = Observable.interval(1, TimeUnit.SECONDS)
                .map(Long::intValue)
                .take(40);

        subscriptions.add(source.subscribe(publishSubject));
        subscriptions.add(source.subscribe(asyncSubject));
        subscriptions.add(source.subscribe(replaySubject));
        subscriptions.add(source.subscribe(behaviorSubject));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        checkAttachedActivity();
        listener = (OnObservableRetrievedListener) getActivity();
    }

    private void checkAttachedActivity() {
        if (! (getActivity() instanceof OnObservableRetrievedListener)) {
            throw new ClassCastException("Attached Activity must implement OnObservableRetrievedListener interface");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        listener.onObservableRetrieved(publishSubject.asObservable(), Subjects.PUBLISH);
        listener.onObservableRetrieved(asyncSubject.asObservable(), Subjects.ASYNC);
        listener.onObservableRetrieved(replaySubject.asObservable(), Subjects.REPLAY);
        listener.onObservableRetrieved(replaySubject.asObservable(), Subjects.BEHAVIOR);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }
}
