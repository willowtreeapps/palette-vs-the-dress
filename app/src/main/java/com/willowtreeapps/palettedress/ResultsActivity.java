package com.willowtreeapps.palettedress;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by derek on 3/3/15.
 */
public class ResultsActivity extends ActionBarActivity {

    private static final String EXTRA_PALETTE_NUMBER = "ResultsActivity_ExtraPaletteNumber";
    private static final String EXTRA_IMAGE_RES_ID = "ResultsActivity_ExtraImageResId";

    private TextView vibrant, vibrantDark, vibrantLight;
    private TextView muted, mutedDark, mutedLight;
    private TextView summaryText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vibrant = (TextView) findViewById(R.id.vibrant);
        vibrantDark = (TextView) findViewById(R.id.vibrant_dark);
        vibrantLight = (TextView) findViewById(R.id.vibrant_light);

        muted = (TextView) findViewById(R.id.muted);
        mutedDark = (TextView) findViewById(R.id.muted_dark);
        mutedLight = (TextView) findViewById(R.id.muted_light);

        summaryText = (TextView) findViewById(R.id.summary_text);

        int numberOfColors = getIntent().getIntExtra(EXTRA_PALETTE_NUMBER, 15);
        int imageResId = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, R.drawable.dress_cropped);

        summaryText.setText("Generating " + numberOfColors + " colors from " + (imageResId == R.drawable.dress_cropped ? "the cropped dress image." : "the full dress image."));
        Palette.generateAsync(BitmapFactory.decodeResource(getResources(), imageResId), numberOfColors, listener);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(final Palette palette) {
            int defaultColor = Color.rgb(0, 0, 0);

            vibrant.setText("Vibrant:\n#" + Integer.toHexString(palette.getVibrantColor(defaultColor)).toUpperCase());
            vibrant.setBackgroundColor(palette.getVibrantColor(defaultColor));
            vibrantDark.setText("Dark Vibrant:\n#" + Integer.toHexString(palette.getDarkVibrantColor(defaultColor)).toUpperCase());
            vibrantDark.setBackgroundColor(palette.getDarkVibrantColor(defaultColor));
            vibrantLight.setText("Light Vibrant:\n#" + Integer.toHexString(palette.getLightVibrantColor(defaultColor)).toUpperCase());
            vibrantLight.setBackgroundColor(palette.getLightVibrantColor(defaultColor));

            if (palette.getVibrantSwatch() != null) {
                vibrant.setTextColor(palette.getVibrantSwatch().getTitleTextColor());
            } else {
                vibrant.setText("Vibrant:\nNot found!");
                vibrant.setBackgroundColor(Color.RED);
            }
            if (palette.getDarkVibrantSwatch() != null) {
                vibrantDark.setTextColor(palette.getDarkVibrantSwatch().getTitleTextColor());
            } else {
                vibrantDark.setText("Dark Vibrant:\nNot found!");
                vibrantDark.setBackgroundColor(Color.RED);
            }
            if (palette.getLightVibrantSwatch() != null) {
                vibrantLight.setTextColor(palette.getLightVibrantSwatch().getTitleTextColor());
            } else {
                vibrantLight.setText("Light Vibrant:\nNot found!");
                vibrantLight.setBackgroundColor(Color.RED);
            }

            muted.setText("Muted:\n#" + Integer.toHexString(palette.getMutedColor(defaultColor)).toUpperCase());
            muted.setBackgroundColor(palette.getMutedColor(defaultColor));
            mutedDark.setText("Dark Muted:\n#" + Integer.toHexString(palette.getDarkMutedColor(defaultColor)).toUpperCase());
            mutedDark.setBackgroundColor(palette.getDarkMutedColor(defaultColor));
            mutedLight.setText("Light Muted:\n#" + Integer.toHexString(palette.getLightMutedColor(defaultColor)).toUpperCase());
            mutedLight.setBackgroundColor(palette.getLightMutedColor(defaultColor));

            if (palette.getMutedSwatch() != null) {
                muted.setTextColor(palette.getMutedSwatch().getTitleTextColor());
            } else {
                muted.setText("Muted:\nNot found!");
                muted.setBackgroundColor(Color.RED);
            }
            if (palette.getDarkMutedSwatch() != null) {
                mutedDark.setTextColor(palette.getDarkMutedSwatch().getTitleTextColor());
            } else {
                mutedDark.setText("Dark Muted:\nNot found!");
                mutedDark.setBackgroundColor(Color.RED);
            }
            if (palette.getLightMutedSwatch() != null) {
                mutedLight.setTextColor(palette.getLightMutedSwatch().getTitleTextColor());
            } else {
                mutedLight.setText("Light Muted:\nNot found!");
                mutedLight.setBackgroundColor(Color.RED);
            }
        }
    };

    public static Intent getLaunchIntent(Context context, int paletteNumber, int checkedRadioButtonId) {
        Intent intent = new Intent(context, ResultsActivity.class);
        intent.putExtra(EXTRA_PALETTE_NUMBER, paletteNumber);
        if (checkedRadioButtonId == R.id.cropped_image) {
            intent.putExtra(EXTRA_IMAGE_RES_ID, R.drawable.dress_cropped);
        } else {
            intent.putExtra(EXTRA_IMAGE_RES_ID, R.drawable.dress_full);
        }
        return intent;
    }
}
