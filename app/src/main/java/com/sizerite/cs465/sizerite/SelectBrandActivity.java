package com.sizerite.cs465.sizerite;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectBrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_brand);
        setTitle("Select a brand");

        // Create a list of all the brands.
        List brands = new ArrayList<Brand>();
        brands.add(new Brand("Nike", "#ffffff"));
        brands.add(new Brand("Adidas", "#ffffff"));
        brands.add(new Brand("Reebok", "#ffffff"));
        brands.add(new Brand("Test", "#ffffff"));
        brands.add(new Brand("Test", "#ff232f"));
        brands.add(new Brand("Test", "#ff214f"));
        brands.add(new Brand("Test", "#fff32f"));
        brands.add(new Brand("Test", "#ffff14"));
        brands.add(new Brand("Test", "#ffff54"));

        // Links the grid to the brand list.
        BrandAdapter adapter = new BrandAdapter(this, (ArrayList<Brand>) brands);
        GridView grid = findViewById(R.id.brand_grid);
        grid.setAdapter(adapter);

        // Set the on click listener for the items.
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                // Clicked item.
                Brand brand = (Brand) parent.getItemAtPosition(position);

                // Start select category activity and pass the name of the brand selected.
                Intent intent = new Intent(view.getContext(), SelectCategoryActivity.class);
                intent.putExtra("brand_selected", brand.Name);
                startActivity(intent);
            }
        });

    }


    // Adapter that converts a brand item to be shown in the grid view.
    public class BrandAdapter extends ArrayAdapter<Brand> {

        public BrandAdapter(Context context, ArrayList<Brand> brands) {
            super(context, 0, brands);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get the data item for this position
            Brand brand = getItem(position);

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_brand, parent, false);
            }

            // Lookup view for data population
            TextView brandName = (TextView) convertView.findViewById(R.id.brand_name);
            ImageView brandImage = (ImageView) convertView.findViewById(R.id.brand_image);
            CardView brandTile = (CardView) convertView.findViewById(R.id.brand_background);

            // Set the tile text and color based on the brand.
            brandName.setText(brand.Name);
            brandTile.setBackgroundColor(Color.parseColor(brand.BackgroundColor));

            int resId = getResources().getIdentifier(brand.Name.toLowerCase(), "drawable", getPackageName());

            // If the resource ID is nul0, the file was not found, and the app will use the default image.
            if (resId != 0) {
                brandImage.setImageResource(resId);
            }


            // Return the completed view to render on screen
            return convertView;
        }


    }

    public class Brand {

        public String Name;
        public String BackgroundColor;

        public Brand(String name) {
            Name = name;
            BackgroundColor = "#ff7d32";
        }

        public Brand(String name, String backgroundColor){
            this(name);
            BackgroundColor = backgroundColor;
        }
    }
}
