package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NoFeasibleSolutionException;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.linear.UnboundedSolutionException;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class Main {

    /**
     * Coefficients for target function variables
     */
    static double[] coefficients;

    /**
     * Constant for target function.
     */
    static double constant;

    /**
     * Number of constraints to be used initially
     */
    static int numInitialConstraints;

    /**
     * Constraints for simplex (initially consisting of just initial constraints, then consisitng of all constrains at the end).
     */
    static LinearConstraint[] constraints;

    /**
     * Given args runs and compares
     * If args starts with "random" followed by number of variables and number of inequalities, inequalities are created randomly
     * @param args target function consisting of the following (each value seperated by a space):
     *             int: number of coefficients in target function
     *             ints separated by spaces: coefficients in target function
     *             ~
     *             int: constant in target function
     *             int: number of constraints
     *             int: number of initial constraints
     *             ints separated by spaces: coefficients in inequality
     *             constraints in the following format:
     *              int: number of coefficients
     *              ints separated by spaces: coefficients in constraint function
     *              String: inequality (EQ, LEQ, or GEQ)
     */
    public static void main(String[] args) {

        initializeFields(args);
        Simplex simplex = new Simplex(coefficients, constant, numInitialConstraints, constraints);
        simplex.run();

        //hardcoded simplex
//        testSimplex();

        //Holds args as fields
        //If args specify random args call random
        //Create instance of Simplex, SignChangingSimplex, StackingSimplex
        //Calls each (each returns its time)
        //Print times

    }

    private static void initializeFields(String[] args) {

        //randomly generated args
        if (args[0].equals("random")) {
            //todo: random args
        }
        //given args
        else {
            //num coefficients
            coefficients = new double[Integer.parseInt(args[0])];
            int index = 1;
            //initialize coefficients
            while (!args[index].equals("~")) {
                coefficients[index - 1] = Double.parseDouble(args[index]);
                index++;
            }
            //skip "~"
            index++;
            //initialize constant
            constant = Double.parseDouble(args[index]);
            index++;
            int numConstraints = Integer.parseInt(args[index]);
            index++;
            //initialize numInitialConstraints
            numInitialConstraints = Integer.parseInt(args[index]);
            index++;
            //initialize constraints
            constraints = new LinearConstraint[numConstraints];
            int constraintIndex = 0;
            while (index < args.length) {
                int numCoefficients = Integer.parseInt(args[index]);
                index++;
                double[] coefficients = new double[numCoefficients];
                for (int i = 0; i < coefficients.length; i++, index++) {
                    coefficients[i] = Double.parseDouble(args[index]);
                }
                String relationshipString = args[index];
                Relationship relationship;
                if (relationshipString.equals("EQ")) {
                    relationship = Relationship.EQ;
                }
                else if (relationshipString.equals("LEQ")) {
                    relationship = Relationship.LEQ;
                }
                else if (relationshipString.equals("GEQ")) {
                    relationship = Relationship.GEQ;
                }
                else {
                    throw new IllegalArgumentException("Provided relationship is not valid (must be EQ, LEQ, or GEQ).");
                }
                index++;
                double value = Double.parseDouble(args[index]);
                index++;
                constraints[constraintIndex] = new LinearConstraint(coefficients, relationship, value);
                constraintIndex++;
            }

            //todo: what if no constraints
            //todo: what if no additional constraints
            //todo: example input
        }

    }

    /**
     * Hardcoded simplex test
     */
    private static void testSimplex() {

        LinearConstraint[] constraints = new LinearConstraint[4];
        constraints[0] = new LinearConstraint(new double[] { 3, 5 }, Relationship.LEQ, 78);
        constraints[1] = new LinearConstraint(new double[] { 4, 1 }, Relationship.LEQ, 36);
        constraints[2] = new LinearConstraint(new double[] { 1, 0 }, Relationship.GEQ, 0);
        constraints[3] = new LinearConstraint(new double[] { 0, 1 }, Relationship.GEQ, 0);
        Simplex simplex = new Simplex(new double[]{5, 4}, 0, constraints.length, constraints);
        simplex.run();

    }

    /**
     * Randomly creates inequalities.
     */
    private void random() {

        //random based off of time
        //for each inequality choose random coefficient for each inequality
        //input inequality information into fields

    }

    //Questions:
    /**
     * How many times should the Simplexes be tested over the same data for average results minimizing outside activity?
     * Do we need the result of Simplex or only the time? (I will still create a result function for tests)
     * Is there a way to ensure creation of "good" random inequalities (those that can potentially have +/-)?
     * Should I limit inequalities to not always have all variables with non-0 coefficients?
     * Should I add inputs from a file?
     */

}
