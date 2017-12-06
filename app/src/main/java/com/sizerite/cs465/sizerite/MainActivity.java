package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.sizerite.cs465.sizerite.HomePage.Post;

public class MainActivity extends AppCompatActivity {

    public enum AppState{
        Newsfeed,
        AddingToWardrobe,
        FindingPerfectSize,
        LoggingOut
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static AppState currentState = AppState.Newsfeed;
    NewsFeed newsFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creates the staggered grid for the news feed and populates it.
        newsFeed = new NewsFeed(this);


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

                // Opens the camera intent to capture photo
                FloatingActionButton shareButton = findViewById(R.id.share_buttom);
                shareButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }

                        v.setVisibility(View.GONE); // return to the news feed after image capture
                    }
                });

                // if the reset box is tapped, then it is assumed that the user wants to go back to the news feed
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
        MenuItem findSizeButton = menu.findItem(R.id.logout_button);
        findSizeButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                MainActivity.currentState = AppState.LoggingOut;
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return true;
    }

    /**
     * Gets the resulting Bitmap image from the thumbnail of the picture that the user has just taken
     * @param requestCode the request that has completed
     * @param resultCode the status of the request
     * @param data the Intent that has just executed the task
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //TODO: Add the bitmap to the newsfeed
//            String filePath = saveThumbnail(imageBitmap);
            Post post = new Post();
            post.externalThumbnail = imageBitmap;
//            post.imageLocation = filePath;
//            Log.d("Does it get here", filePath);
            newsFeed.addItem(post);
        }
    }

//    String saveThumbnail(Bitmap bmp) {
//        FileOutputStream out = null;
//
//        File file = new File(getApplicationContext().getFilesDir(), "Test.jpg");
//        try {
//            out = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (out != null) {
//                    out.close();
//                    MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return file.getAbsolutePath();
//    }
}
