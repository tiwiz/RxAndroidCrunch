package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import it.tiwiz.rxjavacrunch.R;


public class LogAdapter extends RecyclerView.Adapter<LogViewHolder>{

    private ArrayList<LogItem> backups = new ArrayList<>();
    private ArrayList<LogItem> filtered = new ArrayList<>();
    private List<Integer> filteredId = initFilters();

    private List<Integer> initFilters() {
        return Arrays.asList(0, 1, 2, 3);
    }

    public boolean onNewMessage(@NonNull String message, @Subjects.Type String type) {
        LogItem item = LogItem.from(message, type);
        backups.add(item);
        return addItemToFiltered(item);
    }

    private boolean addItemToFiltered(LogItem item) {
        if (item.matches(filteredId)) {
            filtered.add(item);
            notifyItemInserted(filtered.size() - 1);
            return true;
        }
        return false;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.part8_item_detail, parent, false);
        return new LogViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        holder.bindTo(filtered.get(position));
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void filter(List<Integer> selectedItems) {
        filtered.clear();
        filteredId = selectedItems;
        for (LogItem item : backups) {
            if (item.matches(filteredId)) {
                filtered.add(item);
            }
        }
        notifyDataSetChanged();
    }
}
