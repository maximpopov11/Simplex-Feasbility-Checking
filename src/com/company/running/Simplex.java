package com.company.running;

public class Simplex {
    //todo: use two phase simplex:
    // create initial A, b, c using given final A, b, c and the numInitialConstraints to trim to
    // on repeat: 1. run two phase simplex, 2. create min versions of params, 3. run two phase simplex, 4. print, 5. expand if continuing

    /**
     * A for TwoPhaseSimplex
     */
    double[][] A;

    /**
     * b for TwoPhaseSimplex
     */
    double[] b;

    /**
     * c for TwoPhaseSimplex
     */
    double[] c;

    /**
     * Number of constraints to start with.
     */
    int numInitialConstraints;

    /**
     * Initializes fields.
     */
    public Simplex(double[][] A, double[] b, double[] c, int numInitialConstraints) {
        this.A = A;
        this.b = b;
        this.c = c;
        this.numInitialConstraints = numInitialConstraints;
    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @param type determines what type of Simplex to run (Simplex/SignChangingSimplex/StackingSimplex)
     * todo: run on initial constraints, ..., all constraints
     */
    public void run(SimplexType type) {
        switch (type) {
            case SIMPLEX:
                //todo: rather than running test, run with own version of test to print out results as we want them
                // our version should run a minimize, a maximize, and print the results: (min result, max result)
                TwoPhaseSimplex.test(A, b, c);
                break;
            case SIGN_CHANGING_SIMPLEX:
                System.out.println("Sign Changing Simplex not yet implemented");
                break;
            case STACKING_SIMPLEX:
                System.out.println("Stacking Simplex not yet implemented");
                break;
            default:
                throw new IllegalArgumentException("Simplex type argument is invalid. Inputted argument: " + type);
        }
    }

}
