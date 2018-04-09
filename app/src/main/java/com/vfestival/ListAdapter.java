package com.vfestival;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter extends RecyclerView.Adapter {

    String bio, name;
    int thumbnail;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return Artists.name.length;
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mItemImage;
        private TextView mItemText;

        public ListViewHolder(View itemView){
            super(itemView);
            mItemText = itemView.findViewById(R.id.itemText);
            mItemImage = itemView.findViewById(R.id.artistImage);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            name = Artists.name[position];
            bio = Artists.bio[position];
            thumbnail = Artists.thumbnail[position];
            mItemText.setText(name);
            mItemImage.setImageResource(thumbnail);
            bio = Artists.bio[position];
        }

        public void onClick(View view){
            Intent intent = new Intent(view.getContext(), LineUpDetailsActivity.class);
            intent.putExtra("bio", bio);
            intent.putExtra("thumbnail", thumbnail);
            view.getContext().startActivity(intent);
        }

    }
}