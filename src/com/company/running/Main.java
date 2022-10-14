package com.company.running;

import static com.company.running.SimplexType.*;

public class Main {
    /**
     * Runs minimization and maximization over all Simplex types on the hard-coded input.
     * Default constraints are <= bounds. Implement >= bounds by multiplying both expected sides by -1.
     */
    public static void main(String[] args) {

        double[][] constraintVariableCoefficients = {
                {3, 5},
                {4, 1},
                {-1, 0},
                {0, -1},
        };
        double[] constraintConstants = new double[]{78, 36, 0, 0};
        double[] functionVariableCoefficients = new double[]{5, 4};

        Simplex simplex = new Simplex(constraintVariableCoefficients, constraintConstants, functionVariableCoefficients);
        System.out.println("Simplex:");
        simplex.run(SIMPLEX);
        System.out.println("\nSign-Changing Simplex:");
        simplex.run(SIGN_CHANGING_SIMPLEX);
        System.out.println("\nStacking Simplex:");
        simplex.run(STACKING_SIMPLEX);
    }
}
