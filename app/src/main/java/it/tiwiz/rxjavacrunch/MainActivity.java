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
import it.tiwiz.rxjavacrunch.part4.Part4Activity;
import it.tiwiz.rxjavacrunch.part5.Part5Activity;
import it.tiwiz.rxjavacrunch.part6.Part6Activity;
import it.tiwiz.rxjavacrunch.part7.Part7Activity;
import it.tiwiz.rxjavacrunch.part8.Part8Activity;
import it.tiwiz.rxjavacrunch.part9.Part9Activity;


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
        setTapListener(R.id.btnPart4);
        setTapListener(R.id.btnPart5);
        setTapListener(R.id.btnPart6);
        setTapListener(R.id.btnPart7);
        setTapListener(R.id.btnPart8);
        setTapListener(R.id.btnPart9);
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
            case R.id.btnPart4:
                launchIntent = new Intent(this, Part4Activity.class);
                break;
            case R.id.btnPart5:
                launchIntent = new Intent(this, Part5Activity.class);
                break;
            case R.id.btnPart6:
                launchIntent = new Intent(this, Part6Activity.class);
                break;
            case R.id.btnPart7:
                launchIntent = new Intent(this, Part7Activity.class);
                break;
            case R.id.btnPart8:
                launchIntent = new Intent(this, Part8Activity.class);
                break;
            case R.id.btnPart9:
                launchIntent = new Intent(this, Part9Activity.class);
                break;
        }

        return launchIntent;
    }
}
