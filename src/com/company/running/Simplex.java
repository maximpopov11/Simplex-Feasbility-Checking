package com.company.running;

public class Simplex {
    double[][] constraintVariableCoefficients;
    double[] constraintConstants;
    double[] functionVariableCoefficients;
    double[][] constraintVariableCoefficientsMin;
    double[] constraintConstantsMin;
    double[] functionVariableCoefficientsMin;

    /**
     * Creates Simplex instance.
     */
    public Simplex(double[][] constraintVariableCoefficients, double[] constraintConstants,
                   double[] functionVariableCoefficients) {
        this.constraintVariableCoefficients = constraintVariableCoefficients;
        this.constraintConstants = constraintConstants;
        this.functionVariableCoefficients = functionVariableCoefficients;
        setupMin();
    }

    /**
     * Manipulates max parameters to create the min version.
     */
    private void setupMin() {// Transpose constraintVariableCoefficients
        int m = constraintVariableCoefficients.length;
        int n = constraintVariableCoefficients[0].length;
        constraintVariableCoefficientsMin = new double[n][m];
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                constraintVariableCoefficientsMin[x][y] = constraintVariableCoefficients[y][x];
                if (x >= m - n) {
                    constraintVariableCoefficientsMin[x][y] = -constraintVariableCoefficientsMin[x][y];
                }
            }
        }

        // Swap constraintConstants and functionVariableCoefficients
        // Multiply all values by -1
        constraintConstantsMin = new double[functionVariableCoefficients.length];
        for(int i = 0; i < constraintConstantsMin.length; i++) {
            constraintConstantsMin[i] = -functionVariableCoefficients[i];
        }
        functionVariableCoefficientsMin = new double[constraintConstants.length];
        for(int i = 0; i < functionVariableCoefficientsMin.length; i++) {
            functionVariableCoefficientsMin[i] = -constraintConstants[i];
        }
    }

    /**
     * Runs simplex without saving the results, to be tested for time.
     * @param type determines what type of Simplex to run (Simplex/SignChangingSimplex/StackingSimplex)
     */
    public void run(SimplexType type) {
        SimplexMarker max;
        SimplexMarker min;

        switch (type) {
            case SIMPLEX:
                max = new TwoPhaseSimplex(constraintVariableCoefficients, constraintConstants,
                        functionVariableCoefficients);
                min = new TwoPhaseSimplex(constraintVariableCoefficientsMin, constraintConstantsMin,
                        functionVariableCoefficientsMin);
                break;
            case SIGN_CHANGING_SIMPLEX:
                max = new TwoPhaseSignChangingSimplex(constraintVariableCoefficients, constraintConstants,
                        functionVariableCoefficients, true);
                min = new TwoPhaseSignChangingSimplex(constraintVariableCoefficientsMin, constraintConstantsMin,
                        functionVariableCoefficientsMin, false);
                break;
            case STACKING_SIMPLEX:
                System.out.println("Stacking Simplex not yet implemented");
                return;
            default:
                throw new IllegalArgumentException("Simplex type argument is invalid. Inputted argument: " + type);
        }

        System.out.println("Min: " + min.value());
        System.out.println("Max: " + max.value());
        boolean split = min.value() < 0 && max.value() > 0;
        System.out.println("Split: " + split);
    }

}
