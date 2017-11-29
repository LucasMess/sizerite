package com.sizerite.cs465.sizerite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.sizerite.cs465.sizerite.CardGrid.CardGridView;

public class SelectCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        setTitle("Select a category");

        // Create a card grid for categories.
        CardGridView cardGrid = new CardGridView(this);
        cardGrid.createFrom("categories");

        // Bind card grid to view to show it.
        RecyclerView grid = (RecyclerView) findViewById(R.id.category_grid);
        cardGrid.bindTo(grid);

        // Set the activity to transition when an item is clicked.
        switch(MainActivity.currentState){
            case AddingToWardrobe:
                cardGrid.onItemClickTransitionTo(SelectSizeActivity.class);
                break;
            case FindingPerfectSize:
                cardGrid.onItemClickTransitionTo(LoadingScreenActivity.class);
                break;
        }

    }

}
