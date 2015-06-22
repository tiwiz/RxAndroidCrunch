package it.tiwiz.rxjavacrunch;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import it.tiwiz.rxjavacrunch.part1.Part1Activity;
import it.tiwiz.rxjavacrunch.part2.Part2Activity;
import it.tiwiz.rxjavacrunch.part3.Part3Activity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireUi();
    }

    private void wireUi() {
        setTapListener(R.id.btnPart1);
        setTapListener(R.id.btnPart2);
        setTapListener(R.id.btnPart3);
    }

    private void setTapListener(int viewId) {
        findViewById(viewId).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(getLaunchIntentFor(v.getId()));
    }

    private Intent getLaunchIntentFor(int viewId) {
        Intent launchIntent = null;

        switch (viewId) {
            case R.id.btnPart1:
                launchIntent = new Intent(this, Part1Activity.class);
                break;
            case R.id.btnPart2:
                launchIntent = new Intent(this, Part2Activity.class);
                break;
            case R.id.btnPart3:
                launchIntent = new Intent(this, Part3Activity.class);
                break;
        }

        return launchIntent;
    }
}
