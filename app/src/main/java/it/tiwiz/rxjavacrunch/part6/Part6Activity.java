package it.tiwiz.rxjavacrunch.part6;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.support.design.widget.RxSnackbar;
import com.jakewharton.rxbinding.support.v7.widget.RxToolbar;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import it.tiwiz.rxjavacrunch.R;
import rx.functions.Action1;

public class Part6Activity extends AppCompatActivity {
    TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part6);
        responseTextView = (TextView) findViewById(R.id.response);

        manageToolbar();
        manageFloatingActionButton();
        manageEditTexts();
    }

    private void manageToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /**
         * Here, we manage the two events of the toolbar
         **/
        RxToolbar.itemClicks(toolbar).subscribe(this::onToolbarItemClicked);
        RxToolbar.navigationClicks(toolbar).subscribe(aVoid -> onToolbarNavigationClicked());
    }

    private void onToolbarItemClicked(MenuItem menuItem) {
        String message = "Item \"" + menuItem.getTitle() + "\" clicked";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void onToolbarNavigationClicked() {
        Toast.makeText(this, "Navigation item clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.part6, menu);
        return true;
    }

    private void manageFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RxView.clicks(fab).subscribe(aVoid -> onFabClicked());
    }

    private void onFabClicked() {
        Snackbar testSnackbar = Snackbar.make(findViewById(android.R.id.content), "Snackbar clicked", Snackbar.LENGTH_SHORT);
        testSnackbar.show();
        /**
         * Managing the {@link Snackbar} is not that hard either
         */
        RxSnackbar.dismisses(testSnackbar).subscribe(this::onSnackbarDismissed);
    }

    private void onSnackbarDismissed(Integer integer) {
        String text = "Snackbar dismissed with code " + integer;
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void manageEditTexts() {
        EditText usualApproachEditText, reactiveApproachEditText;

        usualApproachEditText = (EditText) findViewById(R.id.editTextUsualApproach);

        usualApproachEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onNewTextChanged(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        /**
         * Here, we see how it can be easy to manage text changes with RxBindings
         */
        reactiveApproachEditText = (EditText) findViewById(R.id.editTextReactiveApproach);
        RxTextView.textChanges(reactiveApproachEditText).subscribe(this::onNewTextChanged);
    }

    private void onNewTextChanged(CharSequence text) {
        responseTextView.setText(text);
    }

}
