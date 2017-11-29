package com.sizerite.cs465.sizerite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.sizerite.cs465.sizerite.CardGrid.Card;
import com.sizerite.cs465.sizerite.CardGrid.CardGridView;

public class SelectBrandActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_brand);
        setTitle("Select a brand");

        // Create a card grid for brands.
        CardGridView cardGrid = new CardGridView(this);
        cardGrid.createFrom("brands");

        // Bind card grid to view to show it.
        RecyclerView grid = (RecyclerView) findViewById(R.id.brand_grid);
        cardGrid.bindTo(grid);
        // Set the activity to transition when an item is clicked.
        cardGrid.onItemClickTransitionTo(SelectCategoryActivity.class);


    }
}
