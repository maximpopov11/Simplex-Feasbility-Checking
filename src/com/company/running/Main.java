package com.company.running;

import static com.company.running.SimplexType.*;

public class Main {
    /**
     * Runs minimization and maximization over all Simplex types on the hard-coded input.
     * Default constraints are <= bounds. Implement >= bounds by multiplying both expected sides by -1.
     */
    public static void main(String[] args) {

        double[][] constraintVariableCoefficients = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
        };
        double[] constraintConstants = new double[]{2, 1, 1, 2};
        double[] functionVariableCoefficients = new double[]{2, 1};

        long startTime;
        long endTime;
        long totalTime;

        Simplex simplex = new Simplex(constraintVariableCoefficients, constraintConstants, functionVariableCoefficients);
        System.out.println("Simplex:");
        startTime = System.nanoTime();
        simplex.run(SIMPLEX);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        System.out.println("\nSign-Changing Simplex:");
        startTime = System.nanoTime();
        simplex.run(SIGN_CHANGING_SIMPLEX);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        System.out.println("\nStacking Simplex:");
        startTime = System.nanoTime();
        simplex.run(STACKING_SIMPLEX);
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
    }
}
