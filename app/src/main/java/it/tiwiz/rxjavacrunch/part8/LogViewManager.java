package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LogViewManager {
    private LogAdapter adapter;

    public LogViewManager(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new LogAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void onNewMessage(@NonNull String message, @Subjects.Type String type) {
        adapter.onNewMessage(message, type);
    }
}
