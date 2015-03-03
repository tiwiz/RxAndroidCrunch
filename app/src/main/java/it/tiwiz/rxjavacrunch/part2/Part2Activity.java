package it.tiwiz.rxjavacrunch.part2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import it.tiwiz.rxjavacrunch.R;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

public class Part2Activity extends ActionBarActivity {

    TextView txtPart1;
    Context context;

    final String[] manyWords = {"Hello", "to", "everyone", "from", "RxAndroid",
            "something", "that", "is", "really", "nice"};

    Action1<String> textViewOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            txtPart1.setText(s);
        }
    };

    Action1<String> toastOnNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    };

    Func1<String, String> toUpperCaseMap = new Func1<String, String>() {
        @Override
        public String call(String s) {
            return s.toUpperCase();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);

        txtPart1 = (TextView) findViewById(R.id.part1text);
        context = this;

        Observable<String> singleObservable = Observable.just("Hello, World!");

        singleObservable.observeOn(AndroidSchedulers.mainThread())
                .map(toUpperCaseMap)
                .subscribe(textViewOnNextAction);

        Observable<String> oneByOneObservable = Observable.from(manyWords);
        oneByOneObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(toastOnNextAction);
    }


}
