package it.tiwiz.rxjavacrunch.part8;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.tiwiz.rxjavacrunch.R;

public class LogViewHolder extends RecyclerView.ViewHolder{
    private final TextView messageView;
    private final ImageView drawableView;

    public LogViewHolder(View itemView) {
        super(itemView);
        messageView = (TextView) itemView.findViewById(R.id.itemMessage);
        drawableView = (ImageView) itemView.findViewById(R.id.itemLogo);
    }

    public void bindTo(@NonNull LogItem item) {
        messageView.setText(item.getText());
        drawableView.setImageResource(item.getDrawable());
    }
}
