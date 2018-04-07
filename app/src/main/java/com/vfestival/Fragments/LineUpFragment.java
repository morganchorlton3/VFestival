package com.vfestival.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vfestival.ListAdapter;
import com.vfestival.R;

public class LineUpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.lineup_fragment, container, false);

        RecyclerView rcview = (RecyclerView) v.findViewById(R.id.ListRecyclerView);

        ListAdapter listadapter = new ListAdapter();
        rcview.setAdapter(listadapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcview.setLayoutManager(layoutManager);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}