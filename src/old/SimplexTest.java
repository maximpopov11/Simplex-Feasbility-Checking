package old;

import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Runs Simplex over specific parameters to test its usage.
 */
public class SimplexTest {

    public static void main(String[] args) {

        //Sets up the optimization problem
        LinearObjectiveFunction function = new LinearObjectiveFunction(new double[]{5, 4}, 0);
        Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
        constraints.add(new LinearConstraint(new double[] { 3, 5 }, Relationship.LEQ, 78));
        constraints.add(new LinearConstraint(new double[] { 4, 1 }, Relationship.LEQ, 36));
        constraints.add(new LinearConstraint(new double[] { 1, 0 }, Relationship.GEQ, 0));
        constraints.add(new LinearConstraint(new double[] { 0, 1 }, Relationship.GEQ, 0));

        //Finds solution
        PointValuePair solution = null;
        try {
            solution = new SimplexSolver().optimize(function, new LinearConstraintSet(constraints), GoalType.MAXIMIZE);
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
            for (int i = 0; i < 2; i++) {
                System.out.print(solution.getPoint()[i] + "\t");
            }
        } else {
            System.out.println("no solution");
        }

    }

}