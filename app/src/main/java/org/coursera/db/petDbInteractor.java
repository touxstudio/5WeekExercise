package org.coursera.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.coursera.adapter.PetAdapter;
import org.coursera.db.DataBase;
import org.coursera.db.DbConstants;
import org.coursera.model.Pets;
import org.coursera.mypentagram.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by TouxStudio on 21/03/2017.
 */

public class petDbInteractor {

    private static final Integer LIKE = 1;
    private Context context;

    public petDbInteractor (Context context) {

        this.context = context;

    }


    public ArrayList<Pets> getDbData(){

      /* ArrayList<Pets> pets = new ArrayList<>();

        pets = new ArrayList<Pets>();

        pets.add(new Pets("Flow", R.drawable.image1, 3 ));
        pets.add(new Pets("Yako", R.drawable.image2, 4));
        pets.add(new Pets("Urkia", R.drawable.image3, 5 ));
        pets.add(new Pets("Bilma", R.drawable.image4, 7));
        pets.add(new Pets("Tuka", R.drawable.image5, 9));

        return pets; */

       DataBase db = new DataBase(context);

        if (!doesDatabaseExist(context, DbConstants.DB_NAME)) {
            insertFakeData(db);
        }


        return db.getAllPets();
    }

    public void insertFakeData(DataBase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_NAME, "Tuka");
        contentValues.put(DbConstants.TABLE_PIC, R.drawable.image2);

        db.insertFakeData(contentValues);

         contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_NAME, "Flow");
        contentValues.put(DbConstants.TABLE_PIC, R.drawable.image1);

        db.insertFakeData(contentValues);

         contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_NAME, "Yako");
        contentValues.put(DbConstants.TABLE_PIC, R.drawable.image3);

        db.insertFakeData(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_NAME, "Bilma");
        contentValues.put(DbConstants.TABLE_PIC, R.drawable.image4);

        db.insertFakeData(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_NAME, "Facka");
        contentValues.put(DbConstants.TABLE_PIC, R.drawable.image5);

        db.insertFakeData(contentValues);
    }

    public void plusLike(Pets pets){
        DataBase db = new DataBase(context);
        ContentValues  contentValues = new ContentValues();
        contentValues.put(DbConstants.TABLE_PET_ID, pets.getId());
        contentValues.put(DbConstants.TABLE_LIKES_LIKES, LIKE);

        db.insertLike(contentValues);
    }

    public int getLikes(Pets pet){
        DataBase db = new DataBase(context);
        return db.getLikes(pet);
    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }


}
