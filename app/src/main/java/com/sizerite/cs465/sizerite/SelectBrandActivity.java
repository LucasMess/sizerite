package com.sizerite.cs465.sizerite;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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
        brands.add(new Brand("Nike", ""));
        brands.add(new Brand("Adidas", ""));
        brands.add(new Brand("Reebok", ""));

        // Links the grid to the brand list.
        BrandAdapter adapter = new BrandAdapter(this, (ArrayList<Brand>)brands);
        GridView grid = findViewById(R.id.brand_grid);
        grid.setAdapter(adapter);

    }


    // Adapter that converts a brand item to be shown in the grid view.
    public class BrandAdapter extends ArrayAdapter<Brand>{

        public BrandAdapter(Context context, ArrayList<Brand> brands){
            super(context, 0, brands);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            // Get the data item for this position
            Brand brand = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_brand, parent, false);
            }
            // Lookup view for data population
            TextView brandName = (TextView) convertView.findViewById(R.id.brand_name);
            ImageView brandImage = (ImageView) convertView.findViewById(R.id.brand_image);
            // Populate the data into the template view using the data object
            brandName.setText(brand.Name);
            //brandName.setBackgroundResource();

            // Return the completed view to render on screen
            return convertView;
        }


    }

    public class Brand{

        public String Name;
        public String ImageLocation;

        public Brand(String name, String imageLocation){
            Name = name;
            ImageLocation = imageLocation;
        }
    }
}
