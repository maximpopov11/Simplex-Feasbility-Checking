package com.company;

public class SignChangingSimplex {

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

        //Find where Simplex steps are done (BFS to BFS)
        //Halt once opposite sign found (+/-)
        //Returns time taken
        return 0;

    }

    //questions:
    /**
     * How can I edit a part of Simplex when it runs as LinearObjectiveFunction through a lot of calls to other classes?
     *   I need to edit a small part of some class in there (the one that goes from BFS to BFS) and only that.
     *   Can/should I copy the entire library and change that small part and use the new library for this
     *   and the old one for normal Simplex?
     *   Or should I just create my own Simplex from scratch?
     */

}
