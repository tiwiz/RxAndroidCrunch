package it.tiwiz.rxjavacrunch.part4.utils;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import it.tiwiz.rxjavacrunch.R;

/**
 *
 */
public class GitHubRepoAdapter {

    public static class RepoHolder extends RecyclerView.ViewHolder {
        private final TextView firstLine;
        private final TextView secondLine;

        public RepoHolder(View itemView) {
            super(itemView);
            firstLine = (TextView) itemView.findViewById(R.id.firstLine);
            secondLine = (TextView) itemView.findViewById(R.id.secondLine);
        }
    }
}
