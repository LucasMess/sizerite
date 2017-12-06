package com.sizerite.cs465.sizerite.HomePage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sizerite.cs465.sizerite.R;
import com.sizerite.cs465.sizerite.WardrobeItem;

import java.util.ArrayList;

/**
 * Created by nathanzurcher on 12/5/17.
 */

public class WardrobeList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<WardrobeItem> wardrobeItems;

    public WardrobeList(Activity context, ArrayList<WardrobeItem> wardrobeItems) {
        super(context, R.layout.item_wardrobe, new String[wardrobeItems.size()]);
        this.context = context;
        this.wardrobeItems = wardrobeItems;
    }

    public void add(WardrobeItem item){
        wardrobeItems.add(item);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_wardrobe, parent, false);
        }

        String brand = wardrobeItems.get(position).brand;
        String category = wardrobeItems.get(position).category;
        String size = wardrobeItems.get(position).size;

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
        sizeTextView.setText(size);

        return convertView;
    }

}