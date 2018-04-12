package com.vfestival.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vfestival.InfoDetailsActivity;
import com.vfestival.R;

public class InfoFragment extends Fragment {

    ListView List;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.info_fragment, container, false);
        List = v.findViewById(R.id.infoList);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.catagories));
        List.setAdapter(mAdapter);
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getResources().getStringArray(R.array.catagories_info);
                Intent intent = new Intent(getActivity(), InfoDetailsActivity.class);
                intent.putExtra("POSITION_KEY", i);
                intent.putExtra("INFO_KEY", getResources().getStringArray(R.array.catagories_info));
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
