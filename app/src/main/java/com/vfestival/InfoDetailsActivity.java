package com.vfestival;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;

public class InfoDetailsActivity extends AppCompatActivity {

    TextView catagory_info;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


       catagory_info = findViewById(R.id.catagory_info);

        int position = getIntent().getExtras().getInt("POSITION_KEY");

        String CatagoryInfoText = getResources().getStringArray(R.array.catagories_info)[position];
        CharSequence styledText = Html.fromHtml(CatagoryInfoText);
        catagory_info.setText(styledText);



    }
}