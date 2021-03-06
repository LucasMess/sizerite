package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sizerite.cs465.sizerite.HomePage.NewsfeedFragment;
import com.sizerite.cs465.sizerite.HomePage.Post;
import com.sizerite.cs465.sizerite.HomePage.WardrobeFragment;

public class MainActivity extends AppCompatActivity
        implements
        NewsfeedFragment.OnFragmentInteractionListener,
        WardrobeFragment.OnFragmentInteractionListener {

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public enum AppState {
        Newsfeed,
        AddingToWardrobe,
        FindingPerfectSize,
        LoggingOut
    }

    public static WardrobeItem wardrobeItem = new WardrobeItem();
    public static NewsfeedFragment newsFeedFragment;
    public static WardrobeFragment wardrobeFragment;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static AppState currentState = AppState.Newsfeed;

    private void changeFragment(String frag) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragmentTransaction.remove(fragment);
        }

        switch (frag) {
            case "newsfeed":
                newsFeedFragment = new NewsfeedFragment();
                fragmentTransaction.add(R.id.content_view, newsFeedFragment);
                fragmentTransaction.commit();
                currentState = AppState.Newsfeed;
                break;
            case "wardrobe":
                wardrobeFragment = new WardrobeFragment();
                fragmentTransaction.add(R.id.content_view, wardrobeFragment);
                fragmentTransaction.commit();
                currentState = AppState.AddingToWardrobe;
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment("newsfeed");

        // Make clicking on plus button take user to the select brand activity.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_to_wardrobe_button);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (currentState == AppState.Newsfeed) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }

                } else {
                    MainActivity.currentState = AppState.AddingToWardrobe;
                    Intent intent = new Intent(getApplicationContext(), SelectBrandActivity.class);
                    startActivity(intent);
                }


                /**
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
                 */
            }
        });

        BottomNavigationView bottomNavigationBar = findViewById(R.id.navigation_bar);
        bottomNavigationBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_newsfeed:
                        changeFragment("newsfeed");
                        break;
                    case R.id.action_wardrobe:
                        changeFragment("wardrobe");
                        break;
                    case R.id.action_find_size:
                        MainActivity.currentState = AppState.FindingPerfectSize;
                        Intent intent = new Intent(getApplicationContext(), SelectBrandActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        bottomNavigationBar.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {

            }
        });


    }

    /**
     * Add the ruler to the top right side of the menu bar.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);

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
     *
     * @param requestCode the request that has completed
     * @param resultCode  the status of the request
     * @param data        the Intent that has just executed the task
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
            newsFeedFragment.newsFeed.addItem(post);
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
