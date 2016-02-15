package it.tiwiz.rxjavacrunch.part7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;

import it.tiwiz.rxjavacrunch.R;
import rx.functions.Action1;

public class Part7Activity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView txtResponse;
    EditText txtNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part7);
        bindToolbar();
        bindFab();
        bindTextViews();
    }

    private void bindToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void bindFab() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this::onCalculationRequest);
    }

    private void bindTextViews() {
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        RxTextView.textChanges(txtNumber).subscribe(this::onNumberStatusInserted);
        txtResponse = (TextView) findViewById(R.id.txtResponse);
    }

    public void onCalculationRequest(View view) {

    }

    public void onNumberStatusInserted(CharSequence charSequence) {
        fab.setEnabled(!TextUtils.isEmpty(charSequence));
    }

}
