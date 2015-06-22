package it.tiwiz.rxjavacrunch.part2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

public class Part2Activity extends AppCompatActivity {

    TextView txtPart1;
    Context context;

    final String[] manyWords = {"Hello", "to", "everyone", "from", "RxAndroid",
            "something", "that", "is", "really", "nice"};

    final List<String> manyWordList = Arrays.asList(manyWords);

    /**
     * We don't need to use a full {@link rx.Subscriber}, we just want to create an
     * action from when we succeed. 
     */
    Action1<String> textViewOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            txtPart1.setText(s);
        }
    };

    /**
     * Creates a Function that will take on a {@link List} of Strings and will emit it one by one
     */
    Func1<List<String>, Observable<String>> getUrls = new Func1<List<String>, Observable<String>>() {
        @Override
        public Observable<String> call (List<String> strings) {
            return Observable.from(strings);
        }
    };

    Action1<String> toastOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Creates a method that will be executed after the {@link rx.Observable}
     * and will return a String to the {@link rx.Subscriber}
     */
    Func1<String, String> toUpperCaseMap = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };


    /**
     * We create a function that merges all the received strings together and emits a single one
     */
    Func2<String, String, String> mergeRoutine = new Func2<String, String, String>() {
        @Override
        public String call (String s, String s1) {
            return String.format("%s %s",s, s1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView) findViewById(R.id.part1text);
        context = this;


        /**
         * Creates an {@link rx.Observable} that will be emitted only once 
         */
        Observable<String> singleObservable = Observable.just("Hello, World!");

        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(toUpperCaseMap)
                .subscribe(textViewOnNextAction);

        /**
         * Emits one item at the time, taking them from any {@link java.util.Collection} 
         */
        Observable<String> oneByOneObservable = Observable.from(manyWords);
        oneByOneObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(toastOnNextAction);

        Observable.just(manyWordList)
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(getUrls)
                .reduce(mergeRoutine)
                .subscribe(toastOnNextAction);

    }


}
