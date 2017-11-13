package com.sizerite.cs465.sizerite;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Used to make an item on the grid that resembles a card.
 * Used mainly for the category and brand selection grid.
 * <p>
 * Created by Lucas on 11/13/2017.
 */
public class CardGrid {

    Context context;
    ArrayList<Card> cards;

    public CardGrid(Context context) {
        this.context = context;
    }

    /**
     * Creates a card grid of the given item type.
     * @param itemName The item type that will be used to create the card grid.
     */
    public void createFrom(String itemName) {
        cards = getCardsFromJson(itemName);
    }

    /**
     * Bind this card grid to a grid, so that it can show the cards in it.
     * @param grid Where the card grid will be shown.
     */
    public void bindTo(GridView grid) {
        Adapter adapter = new Adapter(context, cards);
        grid.setAdapter(adapter);
    }

    /**
     * Adapter used to convert an array of cards into a grid view.
     */
    public class Adapter extends ArrayAdapter<Card> {
        public Adapter(Context context, ArrayList<Card> cards) {
            super(context, 0, cards);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the card at this position.
            Card card = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_card, parent, false);
            }

            // Populate the view with the data of the card.
            return card.populateView(convertView);

        }
    }

    /**
     * Gets a list of cards built from the specified type of items. If given the name "brands",
     * for example, the function will return a list of cards containing data about the brands,
     * so that it can be displayed in a card grid.
     * @param name The name of the item type. This is used to look for the JSON file in the assets folder.
     *             Do not put a file extension.
     * @return  A list of cards of the specified item type.
     */
    private ArrayList<Card> getCardsFromJson(String name) {

        // Get the JSON file based on type.
        JSONObject json = loadJsonFromAssets(name + ".json", context);
        if (json == null) {
            throw new RuntimeException("Could not convert the given type of object to JSON.");
        }

        try {
            ArrayList<Card> cards = new ArrayList();
            JSONArray items = json.getJSONArray(name);

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

        String json = null;
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
     * Creates a Card object from the data in the given JSON object.
     * @param object The JSON object containing the data for the card.
     * @return  The created card.
     */
    private Card createFromJsonObject(JSONObject object) {

        try {
            Card card = new Card();

            // Get the data for the card from the json object, if it exists.
            if (object.has("text"))
                card.text = object.getString("text");

            if (object.has("backgroundColor"))
                card.backgroundColor = object.getString("backgroundColor");

            if (object.has("showText"))
                card.showText = object.getBoolean("showText");

            return card;
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

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
         * @param view The view to be populated.
         * @return The populated view.
         */
        public View populateView(View view) {

            // Lookup the views inside the target view to be populated.
            TextView viewText = (TextView) view.findViewById(R.id.item_name);
            ImageView viewImage = (ImageView) view.findViewById(R.id.item_image);
            CardView viewCard = (CardView) view.findViewById(R.id.item_background);


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
            int resId = view.getResources().getIdentifier(text.toLowerCase(),
                    "drawable", view.getContext().getPackageName());

            // If the resource ID is 0, the file was not found, and the app will use the default image.
            if (resId != 0) {
                viewImage.setImageResource(resId);
            }

            return view;
        }
    }
}
