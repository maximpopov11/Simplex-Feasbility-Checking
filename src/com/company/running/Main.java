package com.company.running;

import java.util.Arrays;

import static com.company.running.SimplexType.*;

public class Main {
    /**
     * Runs minimization and maximization over all Simplex types on the hard-coded input.
     * Default constraints are <= bounds. Implement >= bounds by multiplying both expected sides by -1.
     */
    public static void main(String[] args) {
        //TODO: BUG: sign changing simplex checks before basic feasible solution is found
        // (so checks intermediate infeasible solutions at start)
        int NUM_VARIABLES = 5;
        int NUM_INEQUALITIES = 50;
        int DOMAIN_RADIUS = 1;
        int NUM_TESTS = 10;
        double[] functionCoefficients = generateFunctionCoefficients(NUM_VARIABLES);
        runRandomTests(functionCoefficients, NUM_INEQUALITIES, DOMAIN_RADIUS, NUM_TESTS);
        System.out.println();
        System.out.println();
        System.out.println();
        runRandomTests(functionCoefficients, NUM_INEQUALITIES, DOMAIN_RADIUS, NUM_TESTS);
    }

    /**
     * Generates function coefficients between -100 and 100.
     * @param numVariables number of variables in function (= dimension).
     * @return generated function.
     */
    private static double[] generateFunctionCoefficients(int numVariables) {
        double[] function = new double[numVariables];
        for (int i = 0; i < function.length; i++) {
            function[i] = Math.random() * 200 - 100;
        }
        return function;
    }

    /**
     * Generates constraint constants for inequalities.
     * @param numVariables number of variables (dimension).
     * @param numBoundaryConstraints number of boundary constraints (defining domain).
     * @param numInequalities number of inequalities to generate.
     * @return generated constraint coefficients.
     */
    private static double[][] generateConstraintCoefficients(int numVariables, int numBoundaryConstraints,
                                                             int numInequalities) {
        double[][] constraintCoefficients = new double[numInequalities][numVariables];
        for (int i = 0; i < numVariables; i++) {
            double[] boundaryConstraintPositive = new double[numVariables];
            boundaryConstraintPositive[i] = 1;
            double[] boundaryConstraintNegative = new double[numVariables];
            boundaryConstraintNegative[i] = -1;
            constraintCoefficients[2*i] = boundaryConstraintPositive;
            constraintCoefficients[2*i+1] = boundaryConstraintNegative;
        }
        // We can prove that the set of all equations that fit inside the circle (or higher dimensional figure) defined
        // by the given radius is equal to the set of all equations a^2 + b^2 + ... > 1 for d > 0 and when at least one
        // coefficient ≠ 0.
        // Thus, we generate a series of inequalities in a subset of the legal equations where the coefficients are all
        // > 0.
        for (int i = numBoundaryConstraints; i < constraintCoefficients.length; i++) {
            double[] singleConstraintCoefficients = new double[numVariables];
            for (int j = 0; j < singleConstraintCoefficients.length; j++) {
                // find random value > 1 to ensure legality (losing possibility for some legal equations)
                double coefficient = 0;
                while (coefficient == 0) {
                    coefficient = Math.random();
                }
                while (coefficient < 1) {
                    coefficient *= 10;
                }
                // 50% chance to be negative
                if (Math.random() < 0.5) {
                    coefficient *= -1;
                }
                singleConstraintCoefficients[j] = coefficient;
            }
            constraintCoefficients[i] = singleConstraintCoefficients;
        }
        return constraintCoefficients;
    }

    /**
     * Generates and runs random tests
     * @param functionCoefficients coefficients of target function
     * @param numBoundedInequalities number of inequalities to generate
     * @param domainRadius radius of domain bounding circle/sphere/higher dimensional figure centered at origin. This
     *                     must be positive.
     */
    private static void runRandomTests(double[] functionCoefficients, int numBoundedInequalities, double domainRadius,
                                       int numTests) {
        int numVariables = functionCoefficients.length;
        int numBoundaryConstraints = 2 * numVariables;
        int numInequalities = numBoundaryConstraints + numBoundedInequalities;
        double[] constraintConstants = new double[numInequalities];
        Arrays.fill(constraintConstants, domainRadius);
        double[][] constraintCoefficients = generateConstraintCoefficients(numVariables, numBoundaryConstraints,
                numInequalities);
        // TODO: Run many different tests. Currently runs all <= thus has few bounds. Randomizing = likely infeasible.
        Simplex simplex = new Simplex(constraintCoefficients, constraintConstants, functionCoefficients);
        runTests(simplex);
    }

    /**
     * Runs simplex variations on given simplex and gathers data
     * @param simplex simplex (tableau) instance to run simplex variations over
     */
    private static void runTests(Simplex simplex){
        long startTime;
        long endTime;
        long totalTime;
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
