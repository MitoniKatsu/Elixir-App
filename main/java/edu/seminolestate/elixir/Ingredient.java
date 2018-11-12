package edu.seminolestate.elixir;

public class Ingredient
{
    private int ingredientID;
    private String ingredientName;
    private Measures measureType;
    private float measureAmount;
    private boolean inStock;

    public Ingredient(String name, Measures type, float amount)
    {
        this.ingredientID  = -1; //not yet set
        this.ingredientName = name;
        this.measureType = type;
        this.measureAmount = amount;
        this.inStock = checkStock(name);
    }
    //private methods
    private boolean checkStock(String name)
    {
        //temporary value
        return  true;
    }

    //accessors
    String getIngredientName()
    {
        return ingredientName;
    }

    Measures getMeasureType()
    {
        return measureType;
    }

    float getMeasureAmount()
    {
        return measureAmount;
    }

    boolean isInStock()
    {
        return inStock;
    }

    int getIngredientID()
    {
        return ingredientID;
    }

    void setIngredientID(int ingredientID)
    {
        this.ingredientID = ingredientID;
    }
}
