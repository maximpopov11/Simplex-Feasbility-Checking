package com.company.running;

public class Simplex {
    boolean unrestrictedVariables;

    double[][] constraintVariableCoefficients;
    double[] constraintConstants;
    double[] objectiveFunctionVariableCoefficients;
    double[] objectiveFunctionVariableCoefficientsMin;

    /**
     * Creates Simplex instance.
     */
    public Simplex(double[][] constraintVariableCoefficients, double[] constraintConstants,
                   double[] objectiveFunctionVariableCoefficients, boolean unrestrictedVariables) {
        this.unrestrictedVariables = unrestrictedVariables;
        this.constraintVariableCoefficients = constraintVariableCoefficients;
        this.constraintConstants = constraintConstants;
        this.objectiveFunctionVariableCoefficients = objectiveFunctionVariableCoefficients;
        //minimization multiplies objective function by -1
        this.objectiveFunctionVariableCoefficientsMin = new double[objectiveFunctionVariableCoefficients.length];
        for (int i = 0; i < objectiveFunctionVariableCoefficientsMin.length; i++) {
            objectiveFunctionVariableCoefficientsMin[i] = -objectiveFunctionVariableCoefficients[i];
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
                        objectiveFunctionVariableCoefficients);
                min = new TwoPhaseSimplex(constraintVariableCoefficients, constraintConstants,
                        objectiveFunctionVariableCoefficientsMin);
                break;
            case SIGN_CHANGING_SIMPLEX:
                max = new TwoPhaseSignChangingSimplex(constraintVariableCoefficients, constraintConstants,
                        objectiveFunctionVariableCoefficients, true);
                min = new TwoPhaseSignChangingSimplex(constraintVariableCoefficients, constraintConstants,
                        objectiveFunctionVariableCoefficientsMin, false);
                break;
            case STACKING_SIMPLEX:
                System.out.println("Stacking Simplex not yet implemented");
                return;
            default:
                throw new IllegalArgumentException("Simplex type argument is invalid. Inputted argument: " + type);
        }

        double minValue = -min.value();
        double maxValue = max.value();
        System.out.println("Min: " + minValue);
        System.out.println("Max: " + maxValue);
        boolean split = minValue < 0 && maxValue > 0;
        System.out.println("Split: " + split);
    }

}
