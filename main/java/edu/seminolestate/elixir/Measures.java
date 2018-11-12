package edu.seminolestate.elixir;

public enum Measures
{
    PART(0, "Part", "an equal part"),
    DASH(1, "Dash", "1/32 oz"),
    SPLASH(2, "Splash", "1/12 oz"),
    TEASPOON(3, "Teaspoon", "1/6 oz"),
    TABLESPOON(4, "Tablespoon", "1/2 oz"),
    PONY(5, "Pony", "1 oz"),
    JIGGER(6, "Jigger", "1 1/2 oz"),
    SHOT(7, "Shot", "2 oz"),
    SNIT(8, "Snit", "3 oz"),
    WINEGLASS(9, "Wineglass", "4 oz"),
    SPLIT(10, "Split", "6 oz"),
    CUP(11, "Cup", "8 oz"),
    PINT(12, "Pint", "16 oz"),
    FIFTH(13, "Fifth", "25.6 oz"),
    QUART(14, "Quart", "32 oz"),
    GALLON(15, "Gallon", "128 oz");

    private  int measureInt;
    private String measureString;
    private  String measure;

    Measures(int measureInt, String measureString, String measureOunces)
    {
        this.measureInt = measureInt;
        this.measureString = measureString;
        this.measure = measureOunces;
    }

    int getMeasureInt()
    {
        return measureInt;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString()
    {
        return measureString;
    }

    String toMeasure()
    {
        return measure;
    }

    Measures getMeasureByInt(int measureInt)
    {
        for (Measures measure: Measures.values())
        {
            if (measure.measureInt == measureInt)
                return measure;
        }
        return  null;
    }
}
