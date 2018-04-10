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
import android.widget.Toast;

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
        Artists artist = new Artists("Jay Z", "Born Shawn Corey Carter in New York City on December 4, 1969, Jay-Z grew up in Brooklyn's drug-infested Marcy Projects. He used rap as an escape, appearing for the first time on Yo! MTV Raps in 1989. After selling millions of records with his Roc-a-Fella label,", R.drawable.jay_z);
        artistsList.add(artist);

        artist = new Artists("Coldplay", "Animation, Kids & Family", R.drawable.coldplay);
        artistsList.add(artist);

        artist = new Artists("Bastille", "Animation, Kids & Family", R.drawable.bastille);
        artistsList.add(artist);

        artist = new Artists("George Ezra", "Animation, Kids & Family", R.drawable.george_ezra);
        artistsList.add(artist);

        artist = new Artists("The killers", "Animation, Kids & Family", R.drawable.killers);
        artistsList.add(artist);

        artist = new Artists("The lumineers", "Animation, Kids & Family", R.drawable.lumineers);
        artistsList.add(artist);
        mAdapter.notifyDataSetChanged();
    }
}