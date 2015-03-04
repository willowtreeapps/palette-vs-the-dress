package com.willowtreeapps.palettedress;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText mEditText;
    private Button mButton;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group);

        mEditText = (EditText) findViewById(R.id.edit_text);

        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            int numberOfColors = Integer.parseInt(mEditText.getText().toString());
            startActivity(ResultsActivity.getLaunchIntent(MainActivity.this, numberOfColors, mRadioGroup.getCheckedRadioButtonId()));
        } catch (Exception e) {
            e.printStackTrace();
            startActivity(ResultsActivity.getLaunchIntent(MainActivity.this, 32, mRadioGroup.getCheckedRadioButtonId()));
        }
    }
}
