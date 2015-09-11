package it.tiwiz.rxjavacrunch.part4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import it.tiwiz.rxjavacrunch.R;
import it.tiwiz.rxjavacrunch.part4.utils.GitHubWrapper;
import it.tiwiz.rxjavacrunch.part4.utils.GitHubUsersAdapter;
import it.tiwiz.rxjavacrunch.part4.utils.OnRVItemClickListener;

public class Part4Activity extends AppCompatActivity implements OnRVItemClickListener{

    private final static String TAG = "RxAndroidCrunch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4);

        GitHubUsersAdapter adapter = new GitHubUsersAdapter(this);
        GitHubWrapper.getUsersInto(adapter);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.usersList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onItemClicked(String username) {
        startActivity(Part4DetailActivity.from(this, username));
    }
}
