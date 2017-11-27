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

public class SelectCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        setTitle("Select a category");

        // Create a card grid for categories.
        CardGridView cardGrid = new CardGridView(getApplicationContext());
        cardGrid.createFrom("categories");

        // Bind card grid to view to show it.
        RecyclerView grid = (RecyclerView) findViewById(R.id.category_grid);
        cardGrid.bindTo(grid);

        // Set the on click listener for the items.
//        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//
//                // Clicked item.
//                Card card = (Card) parent.getItemAtPosition(position);
//
//                // Start select category activity and pass the name of the brand selected.
//                Intent intent = new Intent(view.getContext(), SelectSizeActivity.class);
//                intent.putExtra("category_selected", card.text);
//                startActivity(intent);
//            }
//        });


    }

}
