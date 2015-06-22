package it.tiwiz.rxjavacrunch.part1;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class Part1Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    /**
     * Here we create the Action that will be called when a {@link rx.Subscriber}
     * subscribes to our {@link rx.Observable}: every time, we invoke the onNext method
     * with the basic "Hello, World!" string and then we invoke the final method on the
     * {@link rx.Subscriber}, terminating the execution.
     */
    Observable.OnSubscribe observableAction = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello, World!");
            subscriber.onCompleted();
        }
    };

    /**
     * When this {@link rx.Subscriber} is invoked by the {@link rx.Observable}
     * it will change the value inside the {@link android.widget.TextView} in the UI,
     * using as value the parameter sent by the {@link rx.Observable}
     */
    Subscriber<String> textViewSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(String s) {
            txtPart1.setText(s);
        }
    };

    /**
     * When this {@link rx.Subscriber} is invoked by the {@link rx.Observable}
     * it will create a {@link android.widget.Toast},
     * using as message the parameter sent by the {@link rx.Observable}
     */
    Subscriber<String> toastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {}

        @Override
        public void onError(Throwable e) {}

        @Override
        public void onNext(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView) findViewById(R.id.part1text);
        context = this;

        // Let's create an Observable from the Action we declared above
        Observable<String> observable = Observable.create(observableAction);
        /* Since we are going to interact with the objects that are
         * on the UI, we need to observe the outcome of our computation
         * on the main thread
         */
        observable.observeOn(AndroidSchedulers.mainThread());
        // At this point, we can invoke the Subscribers
        observable.subscribe(textViewSubscriber);
        observable.subscribe(toastSubscriber);
    }





}
