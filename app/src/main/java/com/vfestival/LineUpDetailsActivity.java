package com.vfestival;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LineUpDetailsActivity extends AppCompatActivity {

    TextView bio;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_up_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bio = (TextView) findViewById(R.id.itemText);
        image = (ImageView) findViewById(R.id.artistImage);

        int thumbnail = getIntent().getExtras().getInt("thumbnail");
        image.setImageResource(thumbnail);


        String bioText = getIntent().getExtras().getString("bio");
        bio.setText(bioText);
    }
}