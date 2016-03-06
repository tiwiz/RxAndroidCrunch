package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.tiwiz.rxjavacrunch.R;


public class LogAdapter extends RecyclerView.Adapter<LogViewHolder>{

    private ArrayList<LogItem> backups = new ArrayList<>();
    private ArrayList<LogItem> filtered = new ArrayList<>();

    public void onNewMessage(@NonNull String message, @Subjects.Type String type) {
        backups.add(LogItem.from(message, type));
        notifyItemInserted(backups.size() - 1);
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.part8_item_detail, parent, false);
        return new LogViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        holder.bindTo(backups.get(position));
    }

    @Override
    public int getItemCount() {
        return backups.size();
    }
}
