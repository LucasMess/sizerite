package com.sizerite.cs465.sizerite;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by alanfang on 11/15/17.
 */

public class FitSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fit_select);
        setTitle("How well did it fit?");

        SeekBar fitSeekBar = (SeekBar) findViewById(R.id.seekbar);
        fitSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    TextView fitTextView = (TextView) findViewById(R.id.fitText);
                    if (i <= 33) {
                        fitTextView.setText("Tight fit");
                    }
                    else if (i >= 34 && i <= 66){
                        fitTextView.setText("Normal fit");
                    }
                    else {
                        fitTextView.setText("Loose fit");
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    Rect currBounds = seekBar.getThumb().getBounds();
                    currBounds.offset(15, 15);
                    seekBar.getThumb().setBounds(currBounds);
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Rect currBounds = seekBar.getThumb().getBounds();
                    currBounds.offset(-15, -15);
                    seekBar.getThumb().setBounds(currBounds);
                }
            };
}
