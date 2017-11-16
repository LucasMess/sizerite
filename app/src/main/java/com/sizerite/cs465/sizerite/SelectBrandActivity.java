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
import android.widget.TextView;

import java.util.ArrayList;

public class SelectBrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_brand);
        setTitle("Select a brand");

        // Create a card grid for brands.
        CardGrid cardGrid = new CardGrid(getApplicationContext());
        cardGrid.createFrom("brands");

        // Bind card grid to view to show it.
        GridView grid = (GridView)findViewById(R.id.brand_grid);
        cardGrid.bindTo(grid);

        // Set the on click listener for the items.
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                // Clicked item.
                CardGrid.Card card = (CardGrid.Card)parent.getItemAtPosition(position);

                // Start select category activity and pass the name of the brand selected.
                Intent intent = new Intent(view.getContext(), SelectCategoryActivity.class);
                intent.putExtra("brand_selected", card.text);
                startActivity(intent);
            }
        });

    }
}
