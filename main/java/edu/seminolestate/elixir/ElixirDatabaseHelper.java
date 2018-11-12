package edu.seminolestate.elixir;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    void addNewDrink(SQLiteDatabase db, DrinkRecipe drink)
    {
        //drink table
        long newDrinkID = insertDrink(db, drink.getName());
        //drink_ingredients table
        insertDrinkIngredients(db, drink.getIngredients(), (int)newDrinkID);
        //recipe table
        insertRecipe(db, drink.getSteps(), (int)newDrinkID);
    }

    private long insertDrink(SQLiteDatabase db, String name)
    {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        return db.insert("DRINK", null, drinkValues);
    }

    private void insertDrinkIngredients(SQLiteDatabase db, Ingredient[] ingredients, int newDrinkID)
    {
        for (Ingredient ingredient: ingredients)
        {
            ContentValues drinkIngredientValues = new ContentValues();
            drinkIngredientValues.put("DRINK_ID", newDrinkID);
            drinkIngredientValues.put("INGREDIENT_ID", ingredient.getIngredientID());
            drinkIngredientValues.put("MEASUREMENT_TYPE", ingredient.getMeasureType().getMeasureInt());
            drinkIngredientValues.put("MEASUREMENT_AMT", ingredient.getMeasureAmount());
            db.insert("DRINK_INGREDIENTS", null, drinkIngredientValues);
        }
    }

    private void insertRecipe(SQLiteDatabase db, RecipeStep[] steps, int newDrinkID)
    {
        for (RecipeStep step: steps)
        {
            ContentValues recipeValues = new ContentValues();
            recipeValues.put("DRINK_ID", newDrinkID);
            recipeValues.put("STEP_NUMBER", step.getStepNumber());
            recipeValues.put("STEP_TEXT", step.getStepDirections());
        }
    }


    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //create db schema on first run
        if (oldVersion < 1)
        {
            db.execSQL("CREATE TABLE DRINK "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT NOT NULL);");

            db.execSQL("CREATE TABLE INGREDIENT "
            + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NAME TEXT NOT NULL);");

            db.execSQL("CREATE TABLE DRINK_INGREDIENTS "
            + "(DRINK_ID INTEGER PRIMARY KEY, "
            + "INGREDIENT_ID INTEGER PRIMARY KEY, "
            + "MEASUREMENT_TYPE INTEGER NOT NULL, "
            + "MEASUREMENT_AMT REAL NOT NULL, "
            + "FOREIGN KEY (DRINK_ID) REFERENCES DRINK(_id), "
            + "FOREIGN KEY (INGREDIENT_ID) REFERENCES INGREDIENT(_id)"
            + ");");

            db.execSQL("CREATE TABLE RECIPE(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
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
