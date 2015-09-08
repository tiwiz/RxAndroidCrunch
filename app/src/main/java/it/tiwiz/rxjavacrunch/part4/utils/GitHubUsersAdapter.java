package it.tiwiz.rxjavacrunch.part4.utils;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import it.tiwiz.rxjavacrunch.R;
import it.tiwiz.rxjavacrunch.part4.model.GitHubUser;


public class GitHubUsersAdapter extends RecyclerView.Adapter<GitHubUsersAdapter.DataHolder> {

    private List<GitHubUser> users = new ArrayList<>();

    public void addUser(GitHubUser user) {
        users.add(user);
        notifyItemInserted(users.size() - 1);
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.part4_element, viewGroup, false);

        return new DataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataHolder viewHolder, int i) {
        viewHolder.bindTo(users.get(i));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        private final ImageView userPicture;
        private final TextView userName;
        private final TextView userLogin;
        private final TextView userPage;

        public DataHolder(View itemView) {
            super(itemView);
            userPicture = (ImageView) itemView.findViewById(R.id.userPicture);
            userName = (TextView) itemView.findViewById(R.id.userName);
            userLogin = (TextView) itemView.findViewById(R.id.userLogin);
            userPage = (TextView) itemView.findViewById(R.id.userPage);
        }

        public void bindTo(GitHubUser user) {
            userName.setText(user.getName());
            userLogin.setText(user.getLogin());
            userPage.setText(user.getRepos_url());

            Picasso.with(userPicture.getContext())
                    .load(user.getAvatar_url())
                    .placeholder(R.drawable.ic_placeholder)
                    .into(userPicture);
        }
    }
}
