package com.vfestival;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ListAdapter extends RecyclerView.Adapter {

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
            mItemText = (TextView) itemView.findViewById(R.id.itemText);
            mItemImage = (ImageView) itemView.findViewById(R.id.itemImage);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            mItemText.setText(Artists.name[position]);
            mItemImage.setImageResource(Artists.thumbnail[position]);
        }

        public void onClick(View view){
            Intent intent = new Intent(view.getContext(), LineUpDetailsActivity.class);
            view.getContext().startActivity(intent);
        }
        public void onItemClicked(int position) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Name",Artists.name[position]);
            bundle.putSerializable("Bio",Artists.bio[position]);
            bundle.putSerializable("Thumbnail",Artists.thumbnail[position]);
        }

    }
}