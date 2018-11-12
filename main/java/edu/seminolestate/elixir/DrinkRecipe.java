package edu.seminolestate.elixir;

public class DrinkRecipe
{
    private String name;
    private Ingredient[] ingredients;
    private RecipeStep[] steps;
    private boolean ableToMake;

    public DrinkRecipe(
            String name,
            Ingredient[] ingredientList,
            RecipeStep[] stepsToMake)
    {
        this.name = name;
        this.ingredients = ingredientList;
        this.steps = stepsToMake;
        ableToMake = canBeMade(ingredientList);
    }

    //private methods
    private boolean canBeMade(Ingredient[] listOfIngredients)
    {
        boolean allIngredientsInStock = true;

        for (Ingredient ingredient:listOfIngredients)
        {
            if (!ingredient.isInStock())
            {
                allIngredientsInStock = false;
            }
        }

        return allIngredientsInStock;
    }

    //accessors

    String getName()
    {
        return name;
    }

    Ingredient[] getIngredients()
    {
        return ingredients;
    }

    RecipeStep[] getSteps()
    {
        return steps;
    }

    boolean isAbleToMake()
    {
        return ableToMake;
    }
}
