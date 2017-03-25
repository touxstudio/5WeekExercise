package org.coursera.mypentagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.coursera.adapter.PetAdapter;
import org.coursera.model.Pets;

import java.util.ArrayList;

import static org.coursera.mypentagram.R.id.rvPets;

public class Favorites extends AppCompatActivity {

    ArrayList<Pets> pets;
    private RecyclerView petList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        toolbar = (Toolbar) findViewById(R.id.toolBar);

        petList = (RecyclerView) findViewById(rvPets);
        //Recycler View como LinerLayout
        LinearLayoutManager llm = new LinearLayoutManager(this);
        //GridLayoutManager llm = new GridLayoutManager(this, 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        petList.setLayoutManager(llm);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logoo);


        arrayInit();
        adapterInit();
    }


    public void adapterInit(){
        PetAdapter adapter = new PetAdapter(pets, this);
        petList.setAdapter(adapter);
    }


    public void arrayInit(){

        pets = new ArrayList<Pets>();

        pets.add(new Pets("Tuka", R.drawable.image5, 9));
        pets.add(new Pets("Bilma", R.drawable.image4, 7));
        pets.add(new Pets("Urkia", R.drawable.image3, 5 ));
        pets.add(new Pets("Yako", R.drawable.image2, 4));
        pets.add(new Pets("Flow", R.drawable.image1, 3 ));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorites) {
            Intent i = new Intent(this, Favorites.class);
            startActivity(i);
        }
        if(id == R.id.action_contacts){
            Intent i = new Intent(this, ContactActivity.class);
            startActivity(i);
        }
        if(id == R.id.action_about){
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }



}
