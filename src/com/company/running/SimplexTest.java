package com.company.running;

import static com.company.running.SimplexType.SIGN_CHANGING_SIMPLEX;
import static com.company.running.SimplexType.SIMPLEX;

/**
 * Runs Simplex over specific parameters to test its usage.
 */
public class SimplexTest {

    public static void main(String[] args) {
        compareTest();
    }

    private static void compareTest() {
        double[] objectiveFunctionConstants = {-3, 2};
        double[] constraintConstants = {1, 1, 1, 1};
        double[][] constraintCoefficients = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1},
        };
        Simplex simplex = new Simplex(constraintCoefficients, constraintConstants, objectiveFunctionConstants, false);

        System.out.println("Simplex:");
        simplex.run(SIMPLEX);
        System.out.println("\nSign-Changing Simplex:");
        simplex.run(SIGN_CHANGING_SIMPLEX);

        System.out.println("\nUnrestricted variables same problem:");
        double[] objectiveFunctionConstants2 = {-3, 3, 2, -2};
        double[] constraintConstants2 = {1, 1, 1, 1};
        double[][] constraintCoefficients2 = {
                {1, -1, 0, 0},
                {0, 0, 1, -1},
                {-1, 1, 0, 0},
                {0, 0, -1, 1},
        };
        Simplex simplex2 = new Simplex(constraintCoefficients2, constraintConstants2, objectiveFunctionConstants2, true);

        System.out.println("Simplex:");
        simplex2.run(SIMPLEX);
        System.out.println("\nSign-Changing Simplex:");
        simplex2.run(SIGN_CHANGING_SIMPLEX);

        System.out.println("\nUnrestricted variables diamond:");
        double[] objectiveFunctionConstants3 = {-2, 2, 1, -1};
        double[] constraintConstants3 = {1, 1, 1, 1};
        double[][] constraintCoefficients3 = {
                {1, -1, 1, -1},
                {1, -1, -1, 1},
                {-1, 1, 1, -1},
                {-1, 1, -1, 1},
        };
        Simplex simplex3 = new Simplex(constraintCoefficients3, constraintConstants3, objectiveFunctionConstants3, true);

        System.out.println("Simplex:");
        simplex3.run(SIMPLEX);
        System.out.println("\nSign-Changing Simplex:");
        simplex3.run(SIGN_CHANGING_SIMPLEX);
    }

    private static void unrestrictedBoxTest() {
        // maximizing: value = 3 at (1, 1)
        // objective function: 2x1 + 1x2
        // constraint 1: 1x1 + 0x2 <= 1
        // constraint 2: 0x1 + 1x2 <= 1
        // constraint 3: 1x1 + 0x2 >= -1  -->  -1x1 + 0x2 <= 1
        // constraint 4: 0x1 + 1x2 >= -1  -->  0x1 + -1x2 <= 1
        double[] objectiveFunctionConstantsMaxSquare = {2.0, -2.0, 1.0, -1.0};
        double[] constraintConstantsMaxSquare = {1.0, 1.0, 1.0, 1.0};
        double[][] constraintCoefficientsMaxSquare = {
                {1.0, -1.0, 0.0, 0.0},
                {0.0, 0.0, 1.0, -1.0},
                {-1.0, 1.0, 0.0, 0.0},
                {0.0, 0.0, -1.0, 1.0},
        };
        System.out.println("Square around origin max");
        test(constraintCoefficientsMaxSquare, constraintConstantsMaxSquare, objectiveFunctionConstantsMaxSquare);
        double[] objectiveFunctionConstantsMinSquare = {-2.0, 2.0, -1.0, 1.0};
        System.out.println("Square around origin min");
        test(constraintCoefficientsMaxSquare, constraintConstantsMaxSquare, objectiveFunctionConstantsMinSquare);
    }

    private static void restrictedBoxTest() {
        // maximizing: value = 3 at (1, 1)
        // objective function: 2x1 + 1x2
        // constraint 1: 1x1 + 0x2 <= 1
        // constraint 2: 0x1 + 1x2 <= 1
        // constraint 3: 1x1 + 0x2 >= -1  -->  -1x1 + 0x2 <= 1
        // constraint 4: 0x1 + 1x2 >= -1  -->  0x1 + -1x2 <= 1
        double[] objectiveFunctionConstantsMaxSquare = {  -2.0,  -1.0  };
        double[] constraintConstantsMaxSquare = {  1.0, 1.0, 1.0, 1.0  };
        double[][] constraintCoefficientsMaxSquare = {
                {  1.0, 0.0  },
                {  0.0, 1.0  },
                {  -1.0, 0.0  },
                {  0.0, -1.0  },
        };
        System.out.println("Square around origin max");
        test(constraintCoefficientsMaxSquare, constraintConstantsMaxSquare, objectiveFunctionConstantsMaxSquare);
        // results: our xi = simplex xi

        // maximizing: value = -3 at (-1, -1)
        // for minimization: transpose matrix:
        //  transpose constraintCoefficients
        //  swap objectiveFunctionConstants and constraintConstants and multiply both by -1
        double[] constraintConstantsMinSquare = {  -2.0,  -1.0  };
        double[] objectiveFunctionConstantsMinSquare = {  -1.0, -1.0, -1.0, -1.0  };
        double[][] constraintCoefficientsMinSquare = {
                {  1.0, 0.0, -1.0, 0.0  },
                {  0.0, 1.0, 0.0, -1.0  },
        };
        System.out.println("Square around origin min");
        test(constraintCoefficientsMinSquare, constraintConstantsMinSquare, objectiveFunctionConstantsMinSquare);
        // results: our xi = simplex yi * -1



        // maximizing: value = 5 at (2, 1)
        // objective function: 2x1 + 1x2
        // constraint 1: 1x1 + 0x2 <= 2
        // constraint 2: 0x1 + 1x2 <= 1
        // constraint 3: 1x1 + 0x2 >= -1  -->  -1x1 + 0x2 <= 1
        // constraint 4: 0x1 + 1x2 >= -2  -->  0x1 + -1x2 <= 2
        double[] objectiveFunctionConstantsMaxRectangle = {  -2.0,  -1.0  };
        double[] constraintConstantsMaxRectangle = {  2.0, 1.0, 1.0, 2.0  };
        double[][] constraintCoefficientsMaxRectangle = {
                {  1.0, 0.0  },
                {  0.0, 1.0  },
                {  -1.0, 0.0  },
                {  0.0, -1.0  },
        };
        System.out.println("Rectangle around origin max");
        test(constraintCoefficientsMaxRectangle, constraintConstantsMaxRectangle, objectiveFunctionConstantsMaxRectangle);
        // results: our xi = simplex xi

        // maximizing: value = -4 at (-1, -2)
        // for minimization: transpose matrix:
        //  transpose constraintCoefficients
        //  swap objectiveFunctionConstants and constraintConstants and multiply both by -1
        double[] constraintConstantsMinRectangle = {  -2.0,  -1.0  };
        double[] objectiveFunctionConstantsMinRectangle = {  -2.0, -1.0, -1.0, -2.0  };
        double[][] constraintCoefficientsMinRectangle = {
                {  1.0, 0.0, -1.0, 0.0  },
                {  0.0, 1.0, 0.0, -1.0  },
        };
        System.out.println("Rectangle around origin min");
        test(constraintCoefficientsMinRectangle, constraintConstantsMinRectangle, objectiveFunctionConstantsMinRectangle);
        // results: our xi = simplex yi * -1
    }

    private static void test(double[][] A, double[] b, double[] c) {
        TwoPhaseSimplex lp;
        try {
            lp = new TwoPhaseSimplex(A, b, c);
        }
        catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("value = " + lp.value());
        double[] x = lp.primal();
        for (int i = 0; i < x.length; i++)
            System.out.println("x[" + i + "] = " + x[i]);
        double[] y = lp.dual();
        for (int j = 0; j < y.length; j++)
            System.out.println("y[" + j + "] = " + y[j]);
    }

    // x0 = 12, x1 = 28, opt = 800
    private static void test1() {
        double[] c = {  13.0,  23.0 };
        double[] b = { 480.0, 160.0, 1190.0 };
        double[][] A = {
                {  5.0, 15.0 },
                {  4.0,  4.0 },
                { 35.0, 20.0 },
        };
        test(A, b, c);
    }

    // dual of test1():  y0 = 12, y1 = 28, opt = -800
    private static void test2() {
        double[] b = {  -13.0,  -23.0 };
        double[] c = { -480.0, -160.0, -1190.0 };
        double[][] A = {
                {  -5.0, -4.0, -35.0 },
                { -15.0, -4.0, -20.0 }
        };
        test(A, b, c);
    }

    private static void test3() {
        double[][] A = {
                { -1,  1,  0 },
                {  1,  4,  0 },
                {  2,  1,  0 },
                {  3, -4,  0 },
                {  0,  0,  1 },
        };
        double[] c = { 1, 1, 1 };
        double[] b = { 5, 45, 27, 24, 4 };
        test(A, b, c);
    }

    // unbounded
    private static void test4() {
        double[] c = { 2.0, 3.0, -1.0, -12.0 };
        double[] b = {  3.0,   2.0 };
        double[][] A = {
                { -2.0, -9.0,  1.0,  9.0 },
                {  1.0,  1.0, -1.0, -2.0 },
        };
        test(A, b, c);
    }

    // degenerate - cycles if you choose most positive objective function coefficient
    private static void test5() {
        double[] c = { 10.0, -57.0, -9.0, -24.0 };
        double[] b = {  0.0,   0.0,  1.0 };
        double[][] A = {
                { 0.5, -5.5, -2.5, 9.0 },
                { 0.5, -1.5, -0.5, 1.0 },
                { 1.0,  0.0,  0.0, 0.0 },
        };
        test(A, b, c);
    }

    // floating-point EPSILON needed in min-ratio test
    private static void test6() {
        double[] c = { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
        double[] b = { -0.9, 0.2, -0.2, -1.1, -0.7, -0.5, -0.1, -0.1, -1 };
        double[][] A = {
                { -2,  1,  0,  0,  0,  0,  0,  0,  0 },
                {  1, -2, -1,  0,  0,  0,  0,  0,  0 },
                {  0, -1, -2, -1,  0,  0,  0,  0,  0 },
                {  0,  0, -1, -2, -1, -1,  0,  0,  0 },
                {  0,  0,  0, -1, -2, -1,  0,  0,  0 },
                {  0,  0,  0, -1, -1, -2, -1,  0,  0 },
                {  0,  0,  0,  0,  0, -1, -2, -1,  0 },
                {  0,  0,  0,  0,  0,  0, -1, -2, -1 },
                {  0,  0,  0,  0,  0,  0,  0, -1, -2 }
        };
        test(A, b, c);
    }

    // test client
    private static void runTests(String[] args) {
        System.out.println("----- test 1 --------------------");
        test1();

        System.out.println();
        System.out.println("----- test 2 --------------------");
        test2();

        System.out.println();
        System.out.println("----- test 3 --------------------");
        test3();

        System.out.println();
        System.out.println("----- test 4 --------------------");
        test4();

        System.out.println();
        System.out.println("----- test 5 --------------------");
        test5();

        System.out.println();
        System.out.println("----- test 6 --------------------");
        test6();

//        System.out.println("----- test random ---------------");
//        int m = Integer.parseInt(args[0]);
//        int n = Integer.parseInt(args[1]);
//        double[] c = new double[n];
//        double[] b = new double[m];
//        double[][] A = new double[m][n];
//        for (int j = 0; j < n; j++)
//            c[j] = StdRandom.uniform(1000);
//        for (int i = 0; i < m; i++)
//            b[i] = StdRandom.uniform(1000) - 200;
//        for (int i = 0; i < m; i++)
//            for (int j = 0; j < n; j++)
//                A[i][j] = StdRandom.uniform(100) - 20;
//        test(A, b, c);
    }

}