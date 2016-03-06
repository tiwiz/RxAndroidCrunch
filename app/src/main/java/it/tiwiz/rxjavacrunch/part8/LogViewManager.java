package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class LogViewManager {
    private LogAdapter adapter;
    private RecyclerView recyclerView;

    public LogViewManager(@NonNull RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new LogAdapter();
        recyclerView.setAdapter(adapter);
    }

    public void onNewMessage(@NonNull String message, @Subjects.Type String type) {
        if (adapter.onNewMessage(message, type)) {
            recyclerView.scrollToPosition(adapter.getItemCount() - 1);
        }
    }

    public void filter(@NonNull CheckBox publish, @NonNull CheckBox async,
                       @NonNull CheckBox replay, @NonNull CheckBox behavior) {

        List<Integer> selectedIds = new ArrayList<>();
        check(publish, selectedIds, Subjects.PUBLISH_ID);
        check(async, selectedIds, Subjects.ASYNC_ID);
        check(replay, selectedIds, Subjects.REPLAY_ID);
        check(behavior, selectedIds, Subjects.BEHAVIOR_ID);

        adapter.filter(selectedIds);
    }

    private void check(@NonNull CheckBox publish, List<Integer> ids, int id) {
        if (publish.isChecked()) {
            ids.add(id);
        }
    }
}
