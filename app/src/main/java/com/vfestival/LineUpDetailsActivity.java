package com.vfestival;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LineUpDetailsActivity extends AppCompatActivity {

    TextView name, bio;
    ImageView image;
    ImageButton fImg,tImg,wImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_up_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = (TextView) findViewById(R.id.artistName);
        bio = (TextView) findViewById(R.id.artistBio);
        image = (ImageView) findViewById(R.id.artistImage);
        fImg = (ImageButton) findViewById(R.id.fImgButton);
        tImg = (ImageButton) findViewById(R.id.tImgButton);
        wImg = (ImageButton) findViewById(R.id.wImgButton);

        int thumbnail = getIntent().getExtras().getInt("THUMBNAIL_KEY");
        image.setImageResource(thumbnail);

        String nameText = getIntent().getExtras().getString("NAME_KEY");
        name.setText(nameText);

        String bioText = getIntent().getExtras().getString("BIO_KEY");
        bio.setText(bioText);

        final String fLink = getIntent().getExtras().getString("FLINK_KEY");
        fImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent browserF = new Intent(Intent.ACTION_VIEW, Uri.parse(fLink));
                startActivity(browserF);
            }
        });
        final String tLink = getIntent().getExtras().getString("TLINK_KEY");
        tImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent browsert = new Intent(Intent.ACTION_VIEW, Uri.parse(tLink));
                startActivity(browsert);
            }
        });
        final String wLink = getIntent().getExtras().getString("WLINK_KEY");
        wImg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent browsert = new Intent(Intent.ACTION_VIEW, Uri.parse(wLink));
                startActivity(browsert);
            }
        });
    }
}