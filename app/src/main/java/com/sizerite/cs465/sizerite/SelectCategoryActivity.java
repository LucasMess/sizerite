package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class SelectCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        setTitle("Select a category");

        // Create a card grid for categories.
        CardGrid cardGrid = new CardGrid(getApplicationContext());
        cardGrid.createFrom("categories");

        // Bind card grid to view to show it.
        GridView grid = (GridView)findViewById(R.id.category_grid);
        cardGrid.bindTo(grid);

        // Set the on click listener for the items.
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                // Clicked item.
                CardGrid.Card card = (CardGrid.Card) parent.getItemAtPosition(position);

                // Start select category activity and pass the name of the brand selected.
                Intent intent = new Intent(view.getContext(), SelectCategoryActivity.class);
                intent.putExtra("category_selected", card.text);
                startActivity(intent);
            }
        });


    }

}
