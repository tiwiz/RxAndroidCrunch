package it.tiwiz.rxjavacrunch.part5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class Part5Activity extends AppCompatActivity{

    private TextView textEmittedNumber;
    private static final String TAG = "RxAndroidCrunch";

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
        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::logTiming);
    }

    private void logTiming(Long time) {
        textEmittedNumber.setText(String.valueOf(time));
        Log.d(TAG, "Currently emitted number: " + time);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
}
