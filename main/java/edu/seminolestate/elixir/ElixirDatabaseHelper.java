package edu.seminolestate.elixir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ElixirDatabaseHelper extends SQLiteOpenHelper
{
    private static final String DB_NAME = "elixir";
    private static final int DB_VERSION = 1;

    ElixirDatabaseHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        updateDatabase(db, 0, DB_VERSION);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion < 1)
        {
            db.execSQL("CREATE TABLE DRINK "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT NOT NULL);");

            db.execSQL("CREATE TABLE INGREDIENT "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT NOT NULL);");

            db.execSQL("CREATE TABLE RECIPE "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "DRINK_ID INTEGER NOT NULL, "
            + "INGREDIENT_ID INTEGER NOT NULL, "
            + "MEASUREMENT REAL NOT NULL, "
            + "FOREIGN KEY (DRINK_ID) REFERENCES DRINK(_id), "
            + "FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT(_id)"
            + ");");

            db.execSQL("CREATE TABLE DIRECTIONS(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "DRINK_ID INTEGER NOT NULL, "
            + "STEP_NUMBER INTEGER NOT NULL, "
            + "STEP TEXT NOT NULL,"
            + "FOREIGN KEY (DRINK_ID) REFERENCES DRINK(_id)"
            + ");");

            db.execSQL("CREATE TABLE STOCK(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "INGREDIENT_ID INTEGER NOT NULL, "
            + "IN_STOCK INTEGER NOT NULL DEFAULT 0,"
            + "CHECK (IN_STOCK == 0 OR IN_STOCK == 1), "
            + "FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT(_id)"
            + ");");
        }
    }
}
