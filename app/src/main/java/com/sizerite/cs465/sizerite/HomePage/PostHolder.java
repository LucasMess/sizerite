package com.sizerite.cs465.sizerite.HomePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sizerite.cs465.sizerite.R;

/**
 * Contains the views used to display the data.
 *
 * Created by Lucas on 11/20/2017.
 */

public class PostHolder extends RecyclerView.ViewHolder{

    public TextView titleTextView;
    public TextView likesTextView;
    public ImageView imageView;

    public PostHolder(View view){
        super(view);
        titleTextView = view.findViewById(R.id.post_title);
        likesTextView = view.findViewById(R.id.post_likes);
        imageView = view.findViewById(R.id.post_image);
    }
}
