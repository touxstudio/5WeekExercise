package org.coursera.view;

import org.coursera.adapter.PetAdapter;
import org.coursera.model.Pets;

import java.util.ArrayList;

/**
 * Created by TouxStudio on 21/03/2017.
 */

public interface IRecyclerViewFragmentView {


    public void generateLinearLayoutVertical();

    public void generateGridLayoutVertical();

    public PetAdapter createAdapter(ArrayList<Pets> pets);

    public void initAdapter(PetAdapter adapter);
}
