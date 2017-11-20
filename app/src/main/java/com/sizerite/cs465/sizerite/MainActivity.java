package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the staggered grid for the newsfeed and populates it.
        NewsFeed newsFeed = new NewsFeed(this);


        // Make clicking on plus button take user to the select brand activity.
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_to_wardrobe_button);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), SelectBrandActivity.class);
                startActivity(intent);
            }
        });
    }


}
