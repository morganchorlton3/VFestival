package com.vfestival.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vfestival.Artists;
import com.vfestival.LineUpDetailsActivity;
import com.vfestival.ListAdapter;
import com.vfestival.R;
import com.vfestival.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class LineUpFragment extends Fragment {

    private List<Artists> artistsList = new ArrayList<>();
    private ListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.lineup_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.ListRecyclerView);
        mAdapter = new ListAdapter(artistsList);
        recyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Artists artist = artistsList.get(position);
                Intent intent = new Intent(view.getContext(), LineUpDetailsActivity.class);
                intent.putExtra("NAME_KEY", artist.getName());
                intent.putExtra("THUMBNAIL_KEY", artist.getThumbnail());
                intent.putExtra("BIO_KEY", artist.getBio());
                intent.putExtra("FLINK_KEY", artist.getfLink());
                intent.putExtra("TLINK_KEY", artist.gettLink());
                intent.putExtra("WLINK_KEY", artist.getWlink());
                view.getContext().startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private void prepareMovieData() {
        Artists artist = new Artists("Jay Z", R.drawable.jay_z ,"https://www.facebook.com/JayZ/", "https://twitter.com/JAY_Z_Daily?lang=en", "http://lifeandtimes.com/","Born Shawn Corey Carter in New York City on December 4, 1969, Jay-Z grew up in Brooklyn's drug-infested Marcy Projects. He used rap as an escape, appearing for the first time on Yo! MTV Raps in 1989.");
        artistsList.add(artist);

        artist = new Artists("Coldplay", R.drawable.coldplay, "https://www.facebook.com/coldplay/", "https://twitter.com/coldplay?lang=en", "https://en.wikipedia.org/wiki/Coldplay", "Chris Martin (vocals/piano), Jonny Buckland (guitar), Will Champion (drums), and Guy Berryman (bass) were all born into musical households. Martin, the eldest of five, began playing the piano as a young child and later took solace in the work of Tom Waits. Buckland, on the other hand, grew up with the heavy guitar sounds of Eric Clapton and Jimi Hendrix. Scotland native Berryman preferred funk to indie rock, thereby leaving him to play bass, while multi-instrumentalist Champion didn't plan to be a drummer until he joined Coldplay's lineup.");
        artistsList.add(artist);

        artist = new Artists("BΔSTILLE",  R.drawable.bastille, "https://www.facebook.com/bastilleuk/", "https://twitter.com/bastilledan?lang=en", "http://www.bastillebastille.com/","BΔSTILLE are a British rock band formed in 2010. The group began as a solo project by lead vocalist Dan Smith, but later expanded to include keyboardist Kyle Simmons, bassist and guitarist Will Farquarson, and drummer Chris Wood. The name of the band derives from Bastille Day, which is celebrated on 14 July, the date of Smith's birthday.");
        artistsList.add(artist);

        artist = new Artists("George Ezra",  R.drawable.george_ezra, "https://www.facebook.com/georgeezramusic/", "https://twitter.com/george_ezra?lang=en", "http://www.georgeezra.com/","George Ezra Barnett (born 7 June 1993) is an English singer, songwriter, and musician. After releasing two EPs, Did You Hear the Rain? in October 2013 and Cassy O' in March 2014, Ezra rose to prominence with the release of his hit single Budapest, which reached No. 1 in several countries. His debut studio album Wanted on Voyage was released in June 2014, reaching No. 1 in the UK and the top ten in seven other countries. It was also the third best-selling album of 2014 in the UK.");
        artistsList.add(artist);

        artist = new Artists("The killers",  R.drawable.killers, "https://www.facebook.com/Thekillers/", "https://twitter.com/thekillers", "http://www.thekillersmusic.com/","The Killers are a US rock band who formed in Las Vegas, Navada in 2001. The band, which is comprised of Brandon Flowers (vocals), Dave Keuning (guitar), Mark Stoermer (bass) and Ronnie Vannuci (drums), have released four studio albums. Their most recent effort, ‘Battle Born’, was released in 2012. Flowers originally performed with the synth band Blush Response but was fired from the group as he refused to move to Los Angeles. Instead, he stayed in Las Vegas and soon met Keuning; they begin to write songs together and within two weeks had written the future hit single ‘Mr Brightside’. Flowers and Keuning then recruited Stoermer and Vannuci to join The Killers’ line-up.");
        artistsList.add(artist);

        artist = new Artists("The lumineers",  R.drawable.lumineers, "https://www.facebook.com/TheLumineers/", "https://twitter.com/thelumineers", "http://thelumineers.com/","The Lumineers are an American folk rock/Americana band based in Denver, Colorado. The founding members are Wesley Schultz (lead vocals, guitar) and Jeremiah Fraites (drums, percussion). Schultz and Fraites began writing and performing together in Ramsey, New Jersey in 2005. Cellist and vocalist Neyla Pekarek joined the band in 2010. The Lumineers emerged as one of the most popular folk rock/Americana artists during the revival of those genres and their growing popularity in the 2010s. The band's stripped back raw sound draws heavily from artists that influenced Schultz and Fraites such as Bruce Springsteen, Bob Dylan and Tom Petty. They are known for their energetic live shows and several international hit singles including Ho Hey, Stubborn Love, Ophelia and Cleopatra. The band has become one of the top touring bands in the U.S and also sells out shows around the world.");
        artistsList.add(artist);
        mAdapter.notifyDataSetChanged();
    }
}