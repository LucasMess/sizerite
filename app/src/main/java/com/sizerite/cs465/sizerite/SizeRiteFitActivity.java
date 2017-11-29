package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SizeRiteFitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_rite_fit);

        Button fab = (Button)findViewById(R.id.tight);
        Button fab2 = (Button)findViewById(R.id.perfect);
        Button fab3 = (Button)findViewById(R.id.loose);

        final TextView size = (TextView)findViewById(R.id.editText);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                size.setText("31");

            }
        });
        fab2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                size.setText("32");

            }
        });
        fab3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                size.setText("33");

            }
        });

        Button fab4 = (Button)findViewById(R.id.done_sizerite);

        fab4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
