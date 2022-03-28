package com.company.running;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

public class Simplex {

    /**
     * Coefficients for target function variables
     */
    double[] coefficients;

    /**
     * Constant for target function.
     */
    double constant;

    /**
     * Constraints for simplex (starting with some and building up to all).
     */
    LinearConstraint[] constraints;

    /**
     * Number of constraints to start with.
     */
    int startingConstraints;

    /**
     * Initializes fields.
     */
    public Simplex(double[] coefficients, double constant, int numStartingConstraints, LinearConstraint[] constraints) {

        this.coefficients = coefficients;
        this.constant = constant;
        this.startingConstraints = numStartingConstraints;
        this.constraints = constraints;

    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @return double representing time taken to run
     */
    public long run() {

        long startTime = System.currentTimeMillis();
        Collection<LinearConstraint> currentConstraints = new ArrayList<>();
        for (int i = 0; i < startingConstraints; i++) {
            currentConstraints.add(constraints[i]);
        }
        runInstance(currentConstraints);
        //Runs simplex for each additional inequality (+1, +2, ...), does not save result
        for (int i = startingConstraints; i < constraints.length; i++) {
            currentConstraints.add(constraints[i]);
            runInstance(currentConstraints);
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;

    }

    /**
     * Runs an instance of Simplex upon one set of inequalities
     * @param currentConstraints are the inequalities
     */
    private void runInstance(Collection<LinearConstraint> currentConstraints) {

        LinearObjectiveFunction simplex = new LinearObjectiveFunction(coefficients, constant);
        PointValuePair solution = null;

        //max
        try {
            solution = new SimplexSolver().optimize(simplex, new LinearConstraintSet(currentConstraints), GoalType.MAXIMIZE);
        } catch (TooManyIterationsException e) { // ?
            System.out.println("result1: TooManyIterations");
        } catch (NoFeasibleSolutionException e) {
            System.out.println("result1: NoFeasibleSolution");
        } catch (UnboundedSolutionException e) {
            System.out.println("result1: UnboundedSolution");
        }
        System.out.print("Max: ");
        printSolution(solution);

        //min
        try {
            solution = new SimplexSolver().optimize(simplex, new LinearConstraintSet(currentConstraints), GoalType.MINIMIZE);
        } catch (TooManyIterationsException e) { // ?
            System.out.println("result1: TooManyIterations");
        } catch (NoFeasibleSolutionException e) {
            System.out.println("result1: NoFeasibleSolution");
        } catch (UnboundedSolutionException e) {
            System.out.println("result1: UnboundedSolution");
        }
        System.out.print("Min: ");
        printSolution(solution);

    }

    /**
     * Prints simplex solution
     * @param solution to print
     */
    private void printSolution(PointValuePair solution) {

        if (solution != null) {
            // get solution
            double max = solution.getValue();
            System.out.println(max);
            // print decision variables
            for (int j = 0; j < 2; j++) {
                System.out.print(solution.getPoint()[j] + "\t");
            }
            System.out.println();
        } else {
            System.out.println("no max solution");
        }

    }

}
