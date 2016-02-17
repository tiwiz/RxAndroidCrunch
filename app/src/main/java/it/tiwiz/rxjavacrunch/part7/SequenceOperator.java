package it.tiwiz.rxjavacrunch.part7;

import rx.Observable;
import rx.Subscriber;

public class SequenceOperator implements Observable.Operator<Integer, Integer> {

    @Override
    public Subscriber<? super Integer> call(Subscriber<? super Integer> subscriber) {
        return new Subscriber<Integer>(subscriber) {
            @Override
            public void onCompleted() {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onError(e);
                }
            }

            @Override
            public void onNext(Integer integer) {
                if (!subscriber.isUnsubscribed()) {
                    int roundedSqrt = (int) Math.round(Math.sqrt(integer));
                    subscriber.onNext(roundedSqrt);
                }
            }
        };
    }
}
