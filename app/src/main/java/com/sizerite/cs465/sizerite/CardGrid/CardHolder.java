package com.sizerite.cs465.sizerite.CardGrid;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sizerite.cs465.sizerite.R;

/**
 * Created by Lucas on 11/21/2017.
 */

public class CardHolder extends RecyclerView.ViewHolder{

    TextView titleTextView;
    ImageView imageView;
    CardView cardView;

    public CardHolder(View view) {
        super(view);
        // Lookup the views inside the target view to be populated.
        titleTextView = (TextView) view.findViewById(R.id.item_name);
        imageView = (ImageView) view.findViewById(R.id.item_image);
        cardView = (CardView) view.findViewById(R.id.item_background);
    }
}
