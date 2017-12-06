package com.sizerite.cs465.sizerite;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by nathanzurcher on 12/5/17.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] brands;
    private final String[] categories;
    private final int[] sizes;

    public CustomList(Activity context, String[] categories, String[] brands, int[] sizes) {
        super(context, R.layout.item_wardrobe, brands);
        this.context = context;
        this.brands = brands;
        this.categories = categories;
        this.sizes = sizes;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_wardrobe, parent, false);
        }

        String brand = brands[position];
        String category = categories[position];
        int size = sizes[position];

        ImageView brandImageView = convertView.findViewById(R.id.brand_image);
        ImageView categoryImageView = convertView.findViewById(R.id.category_image);
        TextView sizeTextView = convertView.findViewById(R.id.size_text);

        // Get brand logo.
        // Try to find the file with the same name as the text of the card to use as image.
        int resId = context.getResources().getIdentifier(brand.toLowerCase(),
                "drawable", context.getPackageName());
        // If the resource ID is 0, the file was not found, and the app will use the default image.
        if (resId != 0) {
            brandImageView.setImageResource(resId);
        }

        // Get category image.
        // Try to find the file with the same name as the text of the card to use as image.
        resId = context.getResources().getIdentifier(category.toLowerCase(),
                "drawable", context.getPackageName());
        // If the resource ID is 0, the file was not found, and the app will use the default image.
        if (resId != 0) {
            categoryImageView.setImageResource(resId);
        }

        // Set size text.
        sizeTextView.setText("Size: "+String.valueOf(size));

        return convertView;
    }

}