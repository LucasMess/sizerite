package com.sizerite.cs465.sizerite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);
        setTitle("Select a category");
    }
}