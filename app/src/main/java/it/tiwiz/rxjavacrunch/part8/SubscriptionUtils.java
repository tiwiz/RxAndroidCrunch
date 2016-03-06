package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.Nullable;

import rx.subscriptions.CompositeSubscription;

public class SubscriptionUtils {

    public static CompositeSubscription createFrom(@Nullable CompositeSubscription source) {
        if (source == null || source.isUnsubscribed()) {
            return new CompositeSubscription();
        }

        return source;
    }

    public static void safeUnsubscribeFrom(@Nullable CompositeSubscription source) {
        if (source != null) {
            source.unsubscribe();
        }
    }
}
