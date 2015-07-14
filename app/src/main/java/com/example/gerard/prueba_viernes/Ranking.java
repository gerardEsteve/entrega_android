package com.example.gerard.prueba_viernes;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Ranking extends Fragment {

    private static RecyclerView mRecyclerViewx;
    public static ranking_adap rankAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_ranking, container, false);

    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerViewx = (RecyclerView) getActivity().findViewById(R.id.view);
        mRecyclerViewx.setLayoutManager(new LinearLayoutManager(getActivity()));

        rankAdapter = new ranking_adap(getActivity());
        mRecyclerViewx.setAdapter(rankAdapter);

    }
}
