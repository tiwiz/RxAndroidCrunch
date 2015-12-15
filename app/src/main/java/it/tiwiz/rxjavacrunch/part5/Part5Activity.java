package it.tiwiz.rxjavacrunch.part5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

public class Part5Activity extends RxAppCompatActivity {

    private TextView textEmittedNumber;
    private static final String TAG = "NightObserver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        wireUpActivity();
        startEmitting();
    }

    private void wireUpActivity() {
        textEmittedNumber = (TextView) findViewById(R.id.mainEmittedNumber);
    }


    private void startEmitting() {
        Log.d(TAG, "Night gathers, and now my watch begins");
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::logOnNext, this::logOnError, this::logOnCompleted);
    }

    private void logOnNext(Long time) {
        textEmittedNumber.setText(String.valueOf(time));
        Log.d(TAG, "Nothing bad happened for " + time + " seconds");
    }

    private void logOnError(Throwable throwable) {
        Log.e(TAG, "Something worse than White Walkers is approaching!\t" + throwable.getMessage());
    }

    private void logOnCompleted() {
        Log.d(TAG, "The day has come, may my watch end!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "The sun is rising!");
    }
}
