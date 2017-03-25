package org.coursera.presentator;

import android.content.Context;

import org.coursera.model.Pets;
import org.coursera.db.petDbInteractor;
import org.coursera.view.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by TouxStudio on 21/03/2017.
 */

public class RecyclerViewRecyclerViewFragmentPresenterPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private petDbInteractor petDbInteractor;
    private ArrayList<Pets> pets;


    public RecyclerViewRecyclerViewFragmentPresenterPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        getDataDb();
    }

    @Override
    public void getDataDb() {

        petDbInteractor = new petDbInteractor(context);
        pets = petDbInteractor.getDbData();
        showData();

    }

    @Override
    public void showData() {
            iRecyclerViewFragmentView.initAdapter(iRecyclerViewFragmentView.createAdapter(pets));
            iRecyclerViewFragmentView.generateLinearLayoutVertical();

    }


}
