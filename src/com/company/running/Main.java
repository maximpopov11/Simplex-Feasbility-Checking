package com.company.running;

import java.util.Arrays;

import static com.company.running.SimplexType.*;

public class Main {
    /**
     * Runs a batch of tests over a randomly generated function to optimize (max/min) with randomized constraints.
     * args[0] = number of variables (dimension).
     * args[1] = number of constraints to generate.
     * args[2] = side length of the square centered at the origin that generated constraints must intersect.
     * Variables are unrestricted in sign.
     */
    public static void main(String[] args) {
        final int NUM_VARIABLES = Integer.parseInt(args[0]);
        final int NUM_INEQUALITIES = Integer.parseInt(args[1]);
        final int BOUNDARY_DOMAIN_SQUARE_SIDE_LENGTH = Integer.parseInt(args[2]);
        runRandomTests(NUM_VARIABLES, NUM_INEQUALITIES, BOUNDARY_DOMAIN_SQUARE_SIDE_LENGTH);
    }

    private static void runRandomTests(int numVariables, int numBoundedInequalities,
                                       double boundaryDomainSquareSideLength) {
        double[] objectiveFunctionCoefficients = generateObjectiveFunctionCoefficients(numVariables);
        int numBoundaryConstraints = 2 * numVariables;
        int numInequalities = numBoundaryConstraints + numBoundedInequalities;
        double[] constraintConstants = new double[numInequalities];
        Arrays.fill(constraintConstants, boundaryDomainSquareSideLength/2);
        double[][] constraintCoefficients = generateConstraintCoefficients(numVariables, numBoundaryConstraints,
                numInequalities);

        // TODO: Run many different tests:
        //  All combinations of </>: multiply both sides by -1
        //  Catch and ignore infeasible solutions
        Simplex simplex = new Simplex(constraintCoefficients, constraintConstants, objectiveFunctionCoefficients, false);
        runTest(simplex);
    }

    private static double[] generateObjectiveFunctionCoefficients(int numVariables) {
        double[] objectiveFunction = new double[2*numVariables];
        for (int i = 0; i < objectiveFunction.length; i+=2) {
            objectiveFunction[i] = Math.random() - 0.5;
            // allows for unrestricted (+/-) variables
            objectiveFunction[i+1] = -objectiveFunction[i];
        }
        return objectiveFunction;
    }

    private static double[][] generateConstraintCoefficients(int numVariables, int numBoundaryConstraints,
                                                             int numInequalities) {
        double[][] constraintCoefficients = new double[numInequalities][2*numVariables];
        // set up square domain boundary constraints
        for (int i = 0; i < numVariables; i++) {
            double[] boundaryConstraintPositive = new double[2*numVariables];
            boundaryConstraintPositive[2*i] = 1;
            boundaryConstraintPositive[2*i+1] = -1;
            double[] boundaryConstraintNegative = new double[2*numVariables];
            boundaryConstraintNegative[2*i] = -1;
            boundaryConstraintNegative[2*i+1] = 1;
            constraintCoefficients[2*i] = boundaryConstraintPositive;
            constraintCoefficients[2*i+1] = boundaryConstraintNegative;
        }
        // We can prove that the set of all equations that fit inside the circle (or higher dimensional figure)
        // inscribed by the square domain boundary is equal to the set of all equations a^2 + b^2 + ... > 1 for d > 0
        // and when at least one coefficient ≠ 0. Thus, we generate a series of inequalities in a subset of the legal
        // equations where the coefficients are all > 0.
        for (int i = numBoundaryConstraints; i < constraintCoefficients.length; i++) {
            double[] singleConstraintCoefficients = new double[2*numVariables];
            for (int j = 0; j < numVariables; j++) {
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
                singleConstraintCoefficients[2*j] = coefficient;
                singleConstraintCoefficients[2*j+1] = -coefficient;
            }
            constraintCoefficients[i] = singleConstraintCoefficients;
        }
        return constraintCoefficients;
    }

    /**
     * Runs simplex variations on given simplex and gathers data
     * @param simplex simplex (tableau) instance to run simplex variations over
     */
    private static void runTest(Simplex simplex){
        // TODO: add up results
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
