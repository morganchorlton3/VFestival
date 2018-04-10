package com.vfestival;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private List<Artists> artistsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, year, genre;
        public ImageView thumbnail;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.artistName);
            thumbnail = (ImageView) view.findViewById(R.id.artistImage);
        }
    }


    public ListAdapter(List<Artists> moviesList) {
        this.artistsList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Artists artist = artistsList.get(position);
        holder.name.setText(artist.getName());
        holder.thumbnail.setImageResource(artist.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }
}