package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by alanfang on 11/15/17.
 */

public class
SelectFitActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_fit);
        setTitle("How well did it fit?");

        SeekBar fitSeekBar = (SeekBar) findViewById(R.id.seekbar);
        fitSeekBar.setOnSeekBarChangeListener(seekBarListener);

        Button nextButton = findViewById(R.id.nextbutton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    TextView fitTextView = (TextView) findViewById(R.id.fitText);
                    ImageView fitImageView = (ImageView) findViewById(R.id.imageFit);
                    if (i <= 3) {
                        fitTextView.setText("Tight fit");
                        fitImageView.setImageResource(R.drawable.fillertight);
                    }
                    else if (i >= 4 && i <= 7){
                        fitTextView.setText("Normal fit");
                        fitImageView.setImageResource(R.drawable.fillernormal);
                    }
                    else {
                        fitTextView.setText("Loose fit");
                        fitImageView.setImageResource(R.drawable.fillerloose);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}
