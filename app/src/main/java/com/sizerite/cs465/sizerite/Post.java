package com.sizerite.cs465.sizerite;

import android.content.Context;


/**
 * Contains the data of a post.
 *
 * Created by Lucas on 11/16/2017.
 */
public class Post {
    public String title = "No Title";
    public String imageLocation = "ic_dummy_brand";
    public int likeCount = 0;


    public Post() {}

    /**
     * Fill the view holder's fields with the post's data.
     * @param postHolder The post holder to be changed.
     * @param context The context of the app.
     * @return The filled post holder.
     */
    public PostHolder populateViewHolder(PostHolder postHolder, Context context){

        if (title != null)
           postHolder.titleTextView.setText(title);

        postHolder.likesTextView.setText(String.valueOf(likeCount));

        // Try to find the file with the same name as the text of the card to use as image.
        int resId = context.getResources().getIdentifier(imageLocation.toLowerCase(),
                "drawable", context.getPackageName());

        // If the resource ID is 0, the file was not found, and the app will use the default image.
        if (resId != 0) {
            postHolder.imageView.setImageResource(resId);
        }
        return postHolder;
    }
}
