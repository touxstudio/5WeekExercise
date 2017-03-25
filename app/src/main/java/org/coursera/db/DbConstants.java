package org.coursera.db;

/**
 * Created by TouxStudio on 22/03/2017.
 */

public final class DbConstants {

    public static final String DB_NAME = "pets";
    public static final int DB_VERSION = 1;

    // Create table 1
    public static final String TABLE_CREATE_NAME     = "mascotas";
    public static final String TABLE_ID        = "id";
    public static final String TABLE_NAME      = "name";
    public static final String TABLE_PIC       = "pic";

    // Create table 2
    public static final String TABLE_NAME_LIKES      = "likes";
    public static final String TABLE_LIKES_ID        = "id";
    public static final String TABLE_LIKES_LIKES     = "likes";
    public static final String TABLE_PET_ID          = "id_pet";
}
