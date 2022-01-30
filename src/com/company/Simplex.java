package com.company;

public class Simplex {

    /**
     * Initializes fields.
     * @param targetFunction array of doubles representing coefficients and constant (ax + bx` + ... = c)
     * @param initialInequalities 2-d array with columns representing inequalities, coefficients, inequality, and constant
     * @param additionalInequalities 2-d array with columns representing inequalities, coefficients, inequality, and constant
     */
    public void Simplex(double[] targetFunction, double[][] initialInequalities, double[][] additionalInequalities) {

        //Create LinearObjectiveFunction (simplex) as field

    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @return double representing time taken to run
     */
    public double run() {

        //Start timer
        //Runs simplex for each additional inequality (+0, +1, +2, ...), does not save result
        //End timer
        //Returns time taken
        return 0;

    }

    //Questions:
    /**
     * Would it be better to use an array of tuples instead of 2-d array for inequalities to not write 0's for those with few variables?
     * Would an ArrayList of arrays be better for inequalities? No space used for 0's in data but ArrayList functionalities not needed.
     * Would it be preferable to have an array of relationships (index in array defines which inequality) rather than integer representation? I think array of relationships would be clearer.
     */

}
