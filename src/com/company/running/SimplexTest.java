package com.company.running;

/**
 * Runs Simplex over specific parameters to test its usage.
 * To run minmization over same input with constraints becoming <= but same values:
 *  remove slack variables from tableau
 *  multiply bottom row by -1
 *  transpose values
 *  multiply new bottom row by -1 (keeps the negatives in the bottom)
 *  reintroduce slack variables into tableau
 */
public class SimplexTest {

    public static void main(String[] args) {
        int numConstraints;
        int numVariables;
        Simplex simplex;

        numConstraints = 2;
        numVariables = 2 * 2;
        simplex = new Simplex(numConstraints, numVariables);
        // maximizing: value = 78 at (6, 12)
        // function: 5x1 + 4x2
        // constraint 1: 3x1 + 5x2 <= 78
        // constraint 2: 4x1 + x2 <= 36
        float[][] tableauMax = {
                {3,  5,   1, 0,  78},
                {4,  1,   0, 1,  36},
                {-5, -4,  0, 0,  0},
        };
        simplex.fillTable(tableauMax);
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

        // transpose matrix for minimization (constraints are now >=)
        simplex = new Simplex(numConstraints, numVariables);
        float[][] tableauMin = {
                {3,   4,   1, 0, 5},
                {5,   1,   0, 1, 4},
                {-78, -36, 0, 0, 0},
        };
        simplex.fillTable(tableauMin);
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

        // Box area maximization
        numConstraints = 4;
        numVariables = 2 + numConstraints;
        simplex = new Simplex(numConstraints, numVariables);
        // maximizing: value = 3 at (2, 1)
        // function: 2x1 + 1x2
        // constraint 1: 1x1 + 0x2 <= 1
        // constraint 2: 0x1 + 1x2 <= 1
        // constraint 3: 1x1 + 0x2 >= -1
        // constraint 4: 0x1 + 1x2 >= -1
//        float[][] tableauBoxMax = {
//                {1, 0, 1, 0, 0, 0, 1},
//                {0, 1, 0, 1, 0, 0, 1},
//                {1, 0, 0, 0, -1, 0, 1},
//                {0, 1, 0, 0, 0, -1, 1},
//                {-2, -1, 0, 0, 0, 0, 0},
//        };
        numConstraints = 3;
        numVariables = 6;
        simplex = new Simplex(numConstraints, numVariables);
        float[][] tableauBoxMax = {
                {2, 1, 1, 1, 0, 0, 50},
                {2, 1, 0, 0, -1, 0, 36},
                {1, 0, 1, 0, 0, -1, 10},
                {-1, -1, -2, 0, 0, 0, 0},
        };
        simplex.fillTable(tableauBoxMax);
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