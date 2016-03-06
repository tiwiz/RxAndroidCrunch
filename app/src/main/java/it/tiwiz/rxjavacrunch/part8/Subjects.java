package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

public interface Subjects {

    String PUBLISH = "PublishSubject";
    String ASYNC = "AsyncSubject";
    String REPLAY = "ReplaySubject";
    String BEHAVIOR = "BehaviorSubject";

    @StringDef({PUBLISH, ASYNC, REPLAY, BEHAVIOR})
    @interface Type{}

    int PUBLISH_ID = 0;
    int ASYNC_ID = 1;
    int REPLAY_ID = 2;
    int BEHAVIOR_ID = 3;

    @IntDef({PUBLISH_ID, ASYNC_ID, REPLAY_ID, BEHAVIOR_ID})
    @interface Id{}
}
