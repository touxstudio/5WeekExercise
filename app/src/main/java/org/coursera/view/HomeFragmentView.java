package org.coursera.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.coursera.model.Pets;
import org.coursera.adapter.PetAdapter;
import org.coursera.mypentagram.R;
import org.coursera.presentator.IRecyclerViewFragmentPresenter;
import org.coursera.presentator.RecyclerViewRecyclerViewFragmentPresenterPresenter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragmentView extends Fragment implements IRecyclerViewFragmentView {

    private Context context;
    private ArrayList<Pets> pets;
    private RecyclerView petList;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        petList = (RecyclerView) v.findViewById(R.id.rvPets);
        presenter = new RecyclerViewRecyclerViewFragmentPresenterPresenter(this, getContext());
        return v;
    }



     public void scrollToTop(){
        petList.scrollToPosition(0);
        petList.smoothScrollToPosition(0);
    }





    //@Override
    public void generateLinearLayoutVertical() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        //GridLayoutManager llm = new GridLayoutManager(getActivity(), 3);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        petList.setLayoutManager(llm);
    }

    @Override
    public void generateGridLayoutVertical() {

    }

    @Override
    public PetAdapter createAdapter(ArrayList<Pets> pets) {

        PetAdapter adapter = new PetAdapter(pets, getActivity());

        return adapter;

    }


    @Override
    public void initAdapter(PetAdapter adapter) {

        petList.setAdapter(adapter);

    }

}
