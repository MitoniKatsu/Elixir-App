package edu.seminolestate.elixir;

public class RecipeStep
{
    private int stepNumber;
    private String stepDirections;

    public RecipeStep(int number, String directions)
    {
        this.stepNumber = number;
        this.stepDirections = directions;
    }

    //accessors
    int getStepNumber()
    {
        return stepNumber;
    }

    String getStepDirections()
    {
        return stepDirections;
    }
}
