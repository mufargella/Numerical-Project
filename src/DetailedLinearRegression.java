public class DetailedLinearRegression {
    public static void main(String[] args) {
        // Given data
        double[] x = {5, 4, 3, 2, 1};
        double[] y = {1, 2, 3, 4, 5};
        int n = x.length;

        // Print table header
        System.out.println("| i |  X  |  Y  |  X²  |  XY  |");
        System.out.println("|---|-----|-----|------|------|");

        // Initialize sums
        double sumX = 0, sumX2 = 0, sumY = 0, sumXY = 0;

        // Calculate and display table rows
        for (int i = 0; i < n; i++) {
            double xSquared = x[i] * x[i];
            double xy = x[i] * y[i];

            System.out.printf("| %d | %.1f | %.1f | %.1f | %.1f |\n",
                    i+1, x[i], y[i], xSquared, xy);

            sumX += x[i];
            sumX2 += xSquared;
            sumY += y[i];
            sumXY += xy;
        }

        // Display sums row
        System.out.println("|---|-----|-----|------|------|");
        System.out.printf("|Sum| %.1f | %.1f | %.1f | %.1f |\n", sumX, sumY, sumX2, sumXY);
        System.out.println();

        // Display normal equations
        System.out.println("Normal Equations:");
        System.out.printf("1) ∑y = na + b∑x   → %.1f = %da + %.1fb\n", sumY, n, sumX);
        System.out.printf("2) ∑xy = a∑x + b∑x² → %.1f = %.1fa + %.1fb\n", sumXY, sumX, sumX2);
        System.out.println();

        // Calculate coefficients
        double b = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double a = (sumY - b * sumX) / n;

        // Display results
        System.out.println("Calculated Coefficients:");
        System.out.printf("Slope (b) = %.4f\n", b);
        System.out.printf("Intercept (a) = %.4f\n", a);
        System.out.printf("\nRegression Equation: y = %.4f + %.4fx\n", a, b);

        // Estimate y(4.5)
        double xEstimate = 4.5;
        double yEstimate = a + b * xEstimate;
        System.out.printf("\nEstimated y(%.1f) = %.4f\n", xEstimate, yEstimate);
    }
}