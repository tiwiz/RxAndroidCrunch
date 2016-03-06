package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;

import java.util.Locale;

public class TagManager {

    public static String from(@NonNull String type) {
        return String.format(Locale.getDefault(), "[Crunching RxAndroid] - %s", type);
    }
}
