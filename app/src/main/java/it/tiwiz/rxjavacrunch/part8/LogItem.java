package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import static it.tiwiz.rxjavacrunch.R.drawable.ic_async;
import static it.tiwiz.rxjavacrunch.R.drawable.ic_behavior;
import static it.tiwiz.rxjavacrunch.R.drawable.ic_publish;
import static it.tiwiz.rxjavacrunch.R.drawable.ic_replay;
import static it.tiwiz.rxjavacrunch.part8.Subjects.ASYNC;
import static it.tiwiz.rxjavacrunch.part8.Subjects.ASYNC_ID;
import static it.tiwiz.rxjavacrunch.part8.Subjects.BEHAVIOR;
import static it.tiwiz.rxjavacrunch.part8.Subjects.BEHAVIOR_ID;
import static it.tiwiz.rxjavacrunch.part8.Subjects.Id;
import static it.tiwiz.rxjavacrunch.part8.Subjects.PUBLISH;
import static it.tiwiz.rxjavacrunch.part8.Subjects.PUBLISH_ID;
import static it.tiwiz.rxjavacrunch.part8.Subjects.REPLAY;
import static it.tiwiz.rxjavacrunch.part8.Subjects.REPLAY_ID;
import static it.tiwiz.rxjavacrunch.part8.Subjects.Type;

public class LogItem {

    private final String message;
    @Id
    private final int id;
    @DrawableRes
    private final int drawable;

    private LogItem(String message, @Id int id, @DrawableRes int drawable) {
        this.message = message;
        this.id = id;
        this.drawable = drawable;
    }

    public String getText() {
        return message;
    }

    public int getDrawable() {
        return drawable;
    }

    public static LogItem from(@NonNull String message, @Type final String type) {
        switch (type) {
            case ASYNC:
                return new LogItem(message, ASYNC_ID, ic_async);
            case BEHAVIOR:
                return new LogItem(message, BEHAVIOR_ID, ic_behavior);
            case PUBLISH:
                return new LogItem(message, PUBLISH_ID, ic_publish);
            case REPLAY:
            default:
                return new LogItem(message, REPLAY_ID, ic_replay);
        }
    }
}
