package com.sizerite.cs465.sizerite.HomePage;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.sizerite.cs465.sizerite.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Abstraction layer that creates the staggered grid for the news feed, binds it to the activity and
 * implements the recycler view for it.
 *
 * Created by Lucas on 11/16/2017.
 */

public class NewsFeed {

    Context context;
    PostAdapter adapter;
    ArrayList<Post> posts;
    RecyclerView recyclerView;

    public NewsFeed(Context context){
        this.context = context;

        // Generate a list of posts from posts.json.
        posts = getPostsFromJSON();

        // Create the staggered grid.
        RecyclerView postGrid = ((Activity)context).findViewById(R.id.posts_grid);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        postGrid.setLayoutManager(layoutManager);
        postGrid.setHasFixedSize(false);
        recyclerView = postGrid;

        // Bind the grid to the posts.
        adapter = new PostAdapter(posts, context);


        postGrid.setAdapter(adapter);
    }

    /**
     * Gets a list of posts built from the specified type of items. If given the name "brands",
     * for example, the function will return a list of cards containing data about the brands,
     * so that it can be displayed in a card grid.
     * @return  A list of posts.
     */
    private ArrayList<Post> getPostsFromJSON() {

        // Get the JSON file.
        JSONObject json = loadJsonFromAssets("posts.json", context);
        if (json == null) {
            throw new RuntimeException("Could not convert the file to JSON.");
        }

        try {
            ArrayList<Post> cards = new ArrayList();
            JSONArray items = json.getJSONArray("posts");

            for (int i = 0; i < items.length(); i++) {
                cards.add(createFromJsonObject(items.getJSONObject(i)));
            }

            return cards;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Reads the JSON file in the given location and returns it as a JSON object.
     *
     * @param fileName The name of the file to be read.
     * @param context  The activity context.
     * @return The JSON file as a JSON object.
     */
    private JSONObject loadJsonFromAssets(String fileName, Context context) {

        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a Post object from the data in the given JSON object.
     * @param object The JSON object containing the data for the post.
     * @return  The created post.
     */
    private Post createFromJsonObject(JSONObject object) {

        try {
            Post post = new Post();

            // Get the data for the card from the json object, if it exists.
            if (object.has("title"))
                post.title = object.getString("title");

            if (object.has("imageLocation"))
                post.imageLocation = object.getString("imageLocation");

            if (object.has("likeCount"))
                post.likeCount = object.getInt("likeCount");

            return post;
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void addItem(Post newPost) {
        posts.add(0, newPost);
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}


