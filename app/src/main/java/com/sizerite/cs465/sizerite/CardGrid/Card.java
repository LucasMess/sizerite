package com.sizerite.cs465.sizerite.CardGrid;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sizerite.cs465.sizerite.R;

/**
 * Used to store the attributes of a card for the card grid, such as its color, image, and text.
 */
public class Card {
    public String text = "Dummy";
    public String backgroundColor = "#ffffff";
    public boolean showText = true;

    public Card() {
    }

    /**
     * Populates the fields of view's layout based on the card's data.
     * @param cardHolder The cardholder to be populated.
     * @param context The app context.
     * @return The populated cardholder.
     */
    public CardHolder populateCardHolder(CardHolder cardHolder, Context context) {

        // Lookup the views inside the target view to be populated.
        TextView viewText = cardHolder.titleTextView;
        ImageView viewImage = cardHolder.imageView;
        CardView viewCard = cardHolder.cardView;

        // Set the text of the card if needed.
        if (showText) {
            if (viewText != null)
                viewText.setText(text);
        }
        else{
            // Remove default text and center the image vertically.
            viewText.setText("");
            if(viewImage != null) {
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)viewImage.getLayoutParams();
                params.addRule(RelativeLayout.CENTER_VERTICAL);
                viewImage.setLayoutParams(params);
            }
        }

        // Set the background color of the card.
        if (viewCard != null)
            viewCard.setBackgroundColor(Color.parseColor(backgroundColor));

        // Try to find the file with the same name as the text of the card to use as image.
        int resId = context.getResources().getIdentifier(text.toLowerCase(),
                "drawable", context.getPackageName());

        // If the resource ID is 0, the file was not found, and the app will use the default image.
        if (resId != 0) {
            viewImage.setImageResource(resId);
        }

        return cardHolder;
    }
}
