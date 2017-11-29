package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
                final View v = findViewById(R.id.select_action);
                v.setVisibility(View.VISIBLE);

                FloatingActionButton addItemButton = findViewById(R.id.add_new_item_button);
                addItemButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(), SelectBrandActivity.class);
                        MainActivity.currentState = AppState.AddingToWardrobe;
                        startActivity(intent);
                    }
                });

                FloatingActionButton shareButton = findViewById(R.id.share_buttom);
                shareButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, 1);
                        }
                    }
                });

                TextView resetBox = findViewById(R.id.reset_box);
                resetBox.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        v.setVisibility(View.GONE);
                    }
                });
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

    public static void setSecondaryButtonListener() {
    }

}
