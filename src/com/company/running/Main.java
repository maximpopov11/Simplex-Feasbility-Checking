package com.company.running;

import static com.company.running.SimplexType.*;

public class Main {

    public static void main(String[] args) {

        double[][] A = {
                {3, 5},
                {4, 1}
        };
        double[] b = new double[]{78, 36};
        double[] c = new double[]{5, 4};
        //todo: add >= bounds simply by multiplying both expected sides by -1 to have them passed forward as <=
        int numInitialConstraints = 2;

        Simplex simplex = new Simplex(A, b, c, numInitialConstraints);
        System.out.println("Simplex:");
        simplex.run(SIMPLEX);
        System.out.println("\nSign-Changing Simplex:");
        simplex.run(SIGN_CHANGING_SIMPLEX);
        System.out.println("\nStacking Simplex:");
        simplex.run(STACKING_SIMPLEX);

    }

}
