package com.company.running;

/**
 * Runs Simplex over specific parameters to test its usage.
 */
public class SimplexTest {

    public static void main(String[] args) {
        int numConstraints = 2;
        int numVariables = 2 * 2;
        Simplex simplex = new Simplex(numConstraints, numVariables);

        // maximizing: value = 78 at (6, 12)
        // function: 5x1 + 4x2
        // constraint 1: 3x1 + 5x2 <= 78
        // constraint 2: 4x1 + x2 <= 36
        float[][] tableau = {
                {3,  5,   1, 0,  78},
                {4,  1,   0, 1,  36},
                {-5, -4,  0, 0,  0},
        };
        simplex.fillTable(tableau);
        while (true) {
            Simplex.ERROR result = simplex.compute();
            if(result == Simplex.ERROR.IS_OPTIMAL) {
                System.out.println("Optimal Solution Tableau:");
                simplex.print();
                break;
            } else if (result == Simplex.ERROR.UNBOUNDED) {
                System.out.println("Unbounded!");
                break;
            }
        }

    }

}