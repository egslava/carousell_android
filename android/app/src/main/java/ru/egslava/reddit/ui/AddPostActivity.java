package ru.egslava.reddit.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import ru.egslava.reddit.R;
import ru.egslava.reddit.data.DB;
import ru.egslava.reddit.data.PostEntity;

public class AddPostActivity extends AppCompatActivity implements TextView.OnEditorActionListener, TextWatcher {

    private TextView mTextViewIndicator;
    private EditText mEditTextMessage;
    private int mTextViewIndicatorDefaultTextColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_post_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null){        // actually, I'm not sure about NPE here, but better to live without 'back' button than with a crash
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_action_back);
        }

        mTextViewIndicator = (TextView) findViewById(R.id.add_textview_symbols_indicator);
        mTextViewIndicatorDefaultTextColor = mTextViewIndicator.getCurrentTextColor();
        mEditTextMessage = (EditText) findViewById(R.id.add_edittext_message);
        mEditTextMessage.setOnEditorActionListener(this);
        mEditTextMessage.addTextChangedListener(this);


        // yep, it's strange, but it doesn't work if we put it just in XML
        // https://stackoverflow.com/a/17033570
        // Also, I'm not sure it's ok on every device, but I'm running out of time, so, for now, let it be
        if (mEditTextMessage != null) {
            mEditTextMessage.setHorizontallyScrolling(false);
            mEditTextMessage.setLines(10);
        }
        onTextChanged("", 0, 0, 0);
    }

    @Override
    protected void onDestroy() {
        mEditTextMessage.removeTextChangedListener(this);
        super.onDestroy();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE){
            if (v.getText().toString().isEmpty()){
                v.setError( getString(R.string.add_edittext_message_error_empty_text) );
                return true;
            }

            DB.INSTANCE.add(new PostEntity(getString(R.string.post_time_recently), mEditTextMessage.getText().toString(),
                    0, 0, getString(R.string.post_picture_default)));

            setResult(RESULT_OK);
            finish();
//            NavUtils.navigateUpFromSameTask(this);
//            finish();

            return true;
        }
        return false;
    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (s.length() <= 200){
            mTextViewIndicator.setTextColor( mTextViewIndicatorDefaultTextColor );
        } else {
            mTextViewIndicator.setTextColor( getResources().getColor(R.color.red));

        }
        mTextViewIndicator.setText(getString(R.string.add_textview_symbols_indicator_format, s.length()));
    }

    @Override public void afterTextChanged(Editable s) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
