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
        test(A1, b1, c1);

        // we swap b and c, transpose A, and multiply all values by -1
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
        test(A2, b2, c2);

    }

    private static void test(double[][] A, double[] b, double[] c) {
        TwoPhaseSimplex lp;
        try {
            lp = new TwoPhaseSimplex(A, b, c);
        }
        catch (ArithmeticException e) {
            System.out.println(e);
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