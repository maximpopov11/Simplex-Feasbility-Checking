package com.company;

import java.util.ArrayList;
import java.util.Collection;

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
     * Given args runs and compares
     * If args starts with "random" followed by number of variables and number of inequalities, inequalities are created randomly
     * @param args target function, set of initial inequalities, and set of additional inequalities to add one by one
     *             Target function: number of variables followed by variable coefficients and constant (ax + bx` + ... = c)
     *             Initial inequalities: number of inequalities followed by variable coefficients, inequality relation, and constant
     *             Additional inequalities: same as initial inequalities
     */
    public static void main(String[] args) {

        //Holds args as fields
        //If args specify random args call random
        //Create instance of Simplex, SignChangingSimplex, StackingSimplex
        //Calls each (each returns its time)
        //Print times

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
     */

}
