package com.company.running;

/**
 * Runs Simplex over specific parameters to test its usage.
 */
public class SimplexTest {

    public static void main(String[] args) {

        // maximizing: value = 78 at (6, 12)
        // function: 5x1 + 4x2
        // constraint 1: 3x1 + 5x2 <= 78
        // constraint 2: 4x1 + x1 <= 36
        double[][] A1 = {
                {3, 5},
                {4, 1}
        };
        double[] b1 = new double[]{78, 36};
        double[] c1 = new double[]{5, 4};
        TwoPhaseSimplex.test(A1, b1, c1);

        // we swap b and c and transpose A, then multiply all values by -1
        // to get opposite (max/min) version and opposite (>/< constraints)

        //minimizing: value = -78 at (6, 12)
        // function: -5x1 + -4x2
        // constraint 1: 3x1 + 5x2 >= -78
        // constraint 2: 4x1 + x1 >= -36
        // =
        // function: -78x1 + -36x2
        // constraint 1: -3x1 + -4x2 >= -5
        // constraint 2: -5x1 + -x1 >= -4
        double[][] A2 = {
                {-3, -4},
                {-5, -1}
        };
        double[] b2 = new double[]{-5, -4};
        double[] c2 = new double[]{-78, -36};
        TwoPhaseSimplex.test(A2, b2, c2);

    }

}