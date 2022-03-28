package com.company.running;

public class StackingSimplex {

    /**
     * Initializes fields.
     * @param targetFunction array of doubles representing coefficients and constant (ax + bx` + ... = c)
     * @param initialInequalities 2-d array with columns representing inequalities, coefficients, inequality, and constant
     * @param additionalInequalities 2-d array with columns representing inequalities, coefficients, inequality, and constant
     */
    public void StackingSimplex(double[] targetFunction, double[][] initialInequalities, double[][] additionalInequalities) {

        //Create LinearObjectiveFunction (simplex) as field

    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @return double representing time taken to run
     */
    public double run() {

        //Starts timer
        //For each additional inequality (+0, +1, ...):
        //Runs SignChangingSimplexWithResults (same as running SignChangingSimplex but also returns all BFS's)
        //Tests last BFS (the only +/-) and the one before it (test that satisfies new inequality)
        //If last BFS doesn't exist, keep testing nearest to last ones to start at a more ideal BFS
        //If last BFS does exist but second to last does not, start at last BFS and do SignChangingSimplex normally
        //Ends timer
        //Returns time taken

        //BFS information obtained the same way SignChangingSimplex modifies Java Simplex
        return 0;

    }

    //questions:
    /**
     * Calling SignChangingSimplex would increase the time taken, is that okay?
     * Is remembering and trying all BFS's the ideal way of using former information?
     */

}
