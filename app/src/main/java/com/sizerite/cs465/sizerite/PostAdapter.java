package com.sizerite.cs465.sizerite;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Used to populate PostHolders with data from the post list.
 *
 * Created by Lucas on 11/16/2017.
 */
public class PostAdapter extends RecyclerView.Adapter<PostHolder> {

    ArrayList<Post> posts;
    Context context;

    public PostAdapter(ArrayList<Post> posts, Context context){
        this.posts = posts;
        this.context = context;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post,parent, false);

        PostHolder postHolder = new PostHolder(view);
        return postHolder;
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        posts.get(position).populateViewHolder(holder, context);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}