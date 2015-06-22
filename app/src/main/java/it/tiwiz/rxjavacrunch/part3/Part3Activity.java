package it.tiwiz.rxjavacrunch.part3;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class Part3Activity extends AppCompatActivity {

    TextView txtPart1;
    View rootView;
    Context context;

    final String[] manyWords = {"Hello", "to", "everyone", "from", "RxAndroid",
            "something", "that", "is", "really", "nice"};

    final List<String> manyWordList = Arrays.asList(manyWords);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView) findViewById(R.id.part1text);
        rootView = findViewById(R.id.rootView);
        context = this;

        /**
         * Creates an {@link Observable} that will be emitted only once
         */
        Observable<String> singleObservable = Observable.just("Hello, World!");

        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(String::toUpperCase)
                .subscribe(txtPart1::setText);

        /**
         * Emits one item at the time, taking them from any {@link java.util.Collection}
         */
        Observable<String> oneByOneObservable = Observable.from(manyWords);
        oneByOneObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(message -> Toast.makeText(context, message, Toast.LENGTH_SHORT).show());

        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .reduce((s, s1) -> String.format("%s %s", s, s1))
                .subscribe(message -> Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show());

    }

}
