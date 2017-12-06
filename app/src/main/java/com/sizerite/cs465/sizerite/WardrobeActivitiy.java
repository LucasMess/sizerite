package com.sizerite.cs465.sizerite;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class WardrobeActivitiy extends Activity {

    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    } ;
    Integer[] imageId = {
            R.drawable.news_feed1,
            R.drawable.news_feed2,
            R.drawable.news_feed3,
            R.drawable.news_feed4,
            R.drawable.news_feed5,
            R.drawable.news_feed6,
            R.drawable.news_feed7

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_activitiy);

        CustomList adapter = new
                CustomList(WardrobeActivitiy.this, web, imageId);
        list=(ListView)findViewById(R.id.wardrobe_view);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(WardrobeActivitiy.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

    }
}

