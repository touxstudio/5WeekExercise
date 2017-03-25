package org.coursera.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.coursera.model.Pets;

import java.util.ArrayList;

/**
 * Created by TouxStudio on 22/03/2017.
 */

public class DataBase extends SQLiteOpenHelper{

    private Context context;


    public DataBase(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String queryDbCreation = " CREATE TABLE " + DbConstants.TABLE_CREATE_NAME + " ( " +
                    DbConstants.TABLE_ID            + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DbConstants.TABLE_NAME      + " TEXT," +
                    DbConstants.TABLE_PIC       + " INTEGER" +
                    " ) " ;



            String queryDbCreationLikes = "CREATE TABLE " + DbConstants.TABLE_NAME_LIKES + "(" +
                    DbConstants.TABLE_LIKES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DbConstants.TABLE_PET_ID + " INTEGER," +
                    DbConstants.TABLE_LIKES_LIKES + " INTEGER," +
                    " FOREIGN KEY (" + DbConstants.TABLE_PET_ID + ")" +
                    " REFERENCES " + DbConstants.TABLE_CREATE_NAME + "(" + DbConstants.TABLE_ID  + ")"
                    +")";

        db.execSQL(queryDbCreation);
        db.execSQL(queryDbCreationLikes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + DbConstants.TABLE_CREATE_NAME);
        db.execSQL("DROP TABLE IF EXIST " + DbConstants.TABLE_NAME_LIKES);
        onCreate(db);
    }

    public ArrayList<Pets> getAllPets (){
        ArrayList<Pets> pets = new ArrayList<>();


       String query = "SELECT p.* FROM " +
                DbConstants.TABLE_CREATE_NAME +
                " AS p JOIN " + DbConstants.TABLE_LIKES_LIKES +
                " AS l on l."+ DbConstants.TABLE_PET_ID +
                " = p." + DbConstants.TABLE_ID + " ORDER BY l."+ DbConstants.TABLE_LIKES_LIKES + " ASC ";

        Log.v("QUERY", query);

        String query2 = "SELECT * FROM " + DbConstants.TABLE_CREATE_NAME ;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor datos = db.rawQuery(query2, null);

       /* if (!datos.moveToNext()){

            datos = db.rawQuery(query2, null);
        }*/

        pets = new ArrayList<Pets>();

        while(datos.moveToNext()){
            Pets petsNow = new Pets();
            petsNow.setId(datos.getInt(0));
            petsNow.setName(datos.getString(1));
            petsNow.setPic(datos.getInt(2));

            String queryLikes = "SELECT COUNT("+ DbConstants.TABLE_LIKES_LIKES +
                    ") AS likes FROM " + DbConstants.TABLE_LIKES_LIKES +
                    " WHERE " + DbConstants.TABLE_PET_ID + " = " + datos.getInt(0) ;

            Cursor Likes = db.rawQuery(queryLikes, null);
                if (Likes.moveToNext()){
                    petsNow.setRate(Likes.getInt(0));
                }else{
                    petsNow.setRate(0);
                }

            pets.add(petsNow);
        }
        db.close();

        //pets = new ArrayList<Pets>();
        return pets;
    }

    public void insertFakeData(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbConstants.TABLE_CREATE_NAME, null, contentValues);
        db.close();
    }

    public void insertLike(ContentValues contentValues){

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DbConstants.TABLE_NAME_LIKES, null, contentValues);
        db.close();

    }

    public int getLikes(Pets pet) {

        int likes = 0;

        String query = "SELECT COUNT("+ DbConstants.TABLE_LIKES_LIKES +")" +
                " FROM " + DbConstants.TABLE_NAME_LIKES +
                " WHERE " + DbConstants.TABLE_PET_ID + "=" + pet.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

}
