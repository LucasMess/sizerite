package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sizerite.cs465.sizerite.HomePage.NewsFeed;

public class MainActivity extends AppCompatActivity {

    public enum AppState{
        Newsfeed,
        AddingToWardrobe,
        FindingPerfectSize,
    }

    public static AppState currentState = AppState.Newsfeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the staggered grid for the news feed and populates it.
        NewsFeed newsFeed = new NewsFeed(this);


        // Make clicking on plus button take user to the select brand activity.
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.add_to_wardrobe_button);
        fab.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), SelectBrandActivity.class);
                MainActivity.currentState = AppState.AddingToWardrobe;
                startActivity(intent);
            }
        });
    }

    /**
     * Add the ruler to the top right side of the menu bar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        // Set the ruler's on click listener.
        MenuItem findSizeButton = menu.findItem(R.id.find_size_button);
        findSizeButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MainActivity.currentState = AppState.FindingPerfectSize;
                Intent intent = new Intent(getApplicationContext(), SelectBrandActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return true;
    }


}
