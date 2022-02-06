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
    double[] coefficients;

    /**
     * Constant for target function.
     */
    double constant;

    /**
     * Constraints for simplex (initially consisting of just initial constraints, then consisitng of all constrains at the end).
     */
    Collection<LinearConstraint> constraints;

    /**
     * Additional constraints for simplex to add one by one.
     */
    LinearConstraint[] additionalConstraints;

    /**
     * Given args runs and compares
     * If args starts with "random" followed by number of variables and number of inequalities, inequalities are created randomly
     * @param args target function consisting of the following: (seperated by " ")
     *             int: number of coefficients in target function
     *             ints separated by spaces: coefficients in target function
     *             ~
     *             int: constant in target function
     *        while not inputting "~ ~"
     *             ints separated by spaces: coefficients in inequality
     *             ~
     *             inequality in 2 letters: GT (>), GE (>=), ET (=), LE (<=), LT (<)
     *             ~
     *             int: constant in inequality
     *             ~
     *             ~: when finished with initial inequalities
     */
    public void main(String[] args) {

        //Holds args as fields
        //If args specify random args call random
        //Create instance of Simplex, SignChangingSimplex, StackingSimplex
        //Calls each (each returns its time)
        //Print times

        initializeFields(args);
        //todo: run

    }

    private void initializeFields(String[] args) {

        //randomly generated args
        if (args[0].equals("random")) {
            //todo: random args
        }
        //given args
        else {
            //num coefficients
            this.coefficients = new double[Integer.parseInt(args[0])];
            int i = 1;
            //initialize coefficients
            while (!args[i].equals("~")) {
                coefficients[i - 1] = Double.parseDouble(args[i]);
                i++;
            }
            //skip "~"
            i++;
            //initialize constant
            this.constant = Double.parseDouble(args[i]);
            //initialize initial constraints
            i++;
            this.constraints = new ArrayList<LinearConstraint>();
            String input = args[i];
            while (!input.equals("~")) {

                i++;
            }

            //todo: do we need initial/additional inequalities? Would it not just be inequalities originally, taking first 0, then 1, ...?
            //todo: what if no constraints
            //todo: what if no additional constraints
            //todo: example input
        }

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
