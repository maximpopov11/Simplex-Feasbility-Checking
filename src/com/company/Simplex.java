package com.company;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
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
     * Constraints for simplex (initially consisting of just initial constraints, then consisitng of all constrains at the end).
     */
    Collection<LinearConstraint> constraints;

    /**
     * Additional constraints for simplex to add one by one.
     */
    LinearConstraint[] additionalConstraints;

        /**
         * Initializes fields.
         */
    public Simplex(double[] coefficients, double constant, Collection<LinearConstraint> initialConstraints, LinearConstraint[] additionalConstraints) {

        this.coefficients = coefficients;
        this.constant = constant;
        this.constraints = initialConstraints;
        this.additionalConstraints = additionalConstraints;

    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @return double representing time taken to run
     */
    public long run() {

        long startTime = System.currentTimeMillis();
        //Runs simplex for each additional inequality (+0, +1, +2, ...), does not save result
        for (int i = -1; i < additionalConstraints.length; i++) {
            if (i >= 0) {
                for (int j = 0; j <= i; j++) {
                    constraints.add(additionalConstraints[j]);
                }
            }
            LinearObjectiveFunction simplex = new LinearObjectiveFunction(coefficients, constant);
            PointValuePair solution = null;
            try {
                solution = new SimplexSolver().optimize(simplex, new LinearConstraintSet(constraints), GoalType.MAXIMIZE);
            } catch (TooManyIterationsException e) { // ?
                System.out.println("result1: TooManyIterations");
            } catch (NoFeasibleSolutionException e) {
                System.out.println("result1: NoFeasibleSolution");
            } catch (UnboundedSolutionException e) {
                System.out.println("result1: UnboundedSolution");
            }
            //Prints solution
            if (solution != null) {
                // get solution
                double max = solution.getValue();
                System.out.println("Opt: " + max);
                // print decision variables
                for (int j = 0; j < 2; j++) {
                    System.out.print(solution.getPoint()[j] + "\t");
                }
            } else {
                System.out.println("no solution");
            }
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;

    }

}
