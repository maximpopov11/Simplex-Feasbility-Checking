package com.company.running;

public class Simplex {
    double[][] constraintVariableCoefficients;
    double[] constraintConstants;
    double[] functionVariableCoefficients;

    /**
     * Creates Simplex instance.
     */
    public Simplex(double[][] constraintVariableCoefficients, double[] constraintConstants,
                   double[] functionVariableCoefficients) {
        this.constraintVariableCoefficients = constraintVariableCoefficients;
        this.constraintConstants = constraintConstants;
        this.functionVariableCoefficients = functionVariableCoefficients;
    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @param type determines what type of Simplex to run (Simplex/SignChangingSimplex/StackingSimplex)
     */
    public void run(SimplexType type) {
        switch (type) {
            case SIMPLEX:
                TwoPhaseSimplex.runMinMaxSimplex(constraintVariableCoefficients, constraintConstants,
                        functionVariableCoefficients);
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
