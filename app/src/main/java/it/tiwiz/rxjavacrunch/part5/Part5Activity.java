package it.tiwiz.rxjavacrunch.part5;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import it.tiwiz.rxjavacrunch.R;

public class Part5Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView textEmittedNumber, textFragmentStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        wireUpActivity();
    }

    private void wireUpActivity() {
        textEmittedNumber = (TextView) findViewById(R.id.mainEmittedNumber);
        textFragmentStatus = (TextView) findViewById(R.id.fragmentStatus);
        findViewById(R.id.btnShowFragment).setOnClickListener(this);
        findViewById(R.id.btnHideFragment).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowFragment) {
            addFragment();
        } else {
            removeFragment();
        }
    }

    private void addFragment() {

    }

    private void removeFragment() {

    }
}
