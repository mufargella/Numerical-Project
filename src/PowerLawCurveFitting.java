import java.lang.Math;

public class PowerLawCurveFitting {
    public static void main(String[] args) {
        // Given data
        double[] x = {2.5, 3.5, 5.0, 6.0, 7.5, 10.0};
        double[] y = {5.0, 3.4, 2.0, 1.6, 1.2, 0.8};
        int n = x.length;

        // Print table header
        System.out.println("Power Law Curve Fitting: y = ax^b");
        System.out.println("Original Data:");
        System.out.println("| i |   X   |   Y   |  log(X) |  log(Y) | log(X)² | log(X)log(Y) |");
        System.out.println("|---|-------|-------|---------|---------|---------|-------------|");

        // Initialize sums
        double sumLogX = 0, sumLogX2 = 0, sumLogY = 0, sumLogXY = 0;

        // Calculate and display table rows
        for (int i = 0; i < n; i++) {
            double logX = Math.log(x[i]);
            double logY = Math.log(y[i]);
            double logX2 = logX * logX;
            double logXY = logX * logY;

            System.out.printf("| %d | %5.2f | %5.2f | %7.4f | %7.4f | %7.4f | %11.4f |\n",
                    i+1, x[i], y[i], logX, logY, logX2, logXY);

            sumLogX += logX;
            sumLogX2 += logX2;
            sumLogY += logY;
            sumLogXY += logXY;
        }

        // Display sums row
        System.out.println("|---|-------|-------|---------|---------|---------|-------------|");
        System.out.printf("|Sum|       |       | %7.4f | %7.4f | %7.4f | %11.4f |\n",
                sumLogX, sumLogY, sumLogX2, sumLogXY);
        System.out.println();

        // Display normal equations
        System.out.println("Transformed Linear Equations (after taking logs):");
        System.out.printf("1) ∑log(Y) = nA + b∑log(X)   → %.4f = %dA + %.4fb\n", sumLogY, n, sumLogX);
        System.out.printf("2) ∑log(X)log(Y) = A∑log(X) + b∑log(X)² → %.4f = %.4fA + %.4fb\n",
                sumLogXY, sumLogX, sumLogX2);
        System.out.println();

        // Calculate coefficients
        double b = (n * sumLogXY - sumLogX * sumLogY) / (n * sumLogX2 - sumLogX * sumLogX);
        double A = (sumLogY - b * sumLogX) / n;
        double a = Math.exp(A);

        // Display results
        System.out.println("Calculated Coefficients:");
        System.out.printf("A = log(a) = %.4f\n", A);
        System.out.printf("Exponent (b) = %.4f\n", b);
        System.out.printf("Coefficient (a) = e^A = %.4f\n", a);
        System.out.printf("\nFinal Power Law Equation: y = %.4f * x^%.4f\n", a, b);

//        // Calculate R-squared value
//        double ssTotal = 0;
//        double ssResidual = 0;
//        double meanLogY = sumLogY / n;
//
//        for (int i = 0; i < n; i++) {
//            double logY = Math.log(y[i]);
//            double predictedLogY = A + b * Math.log(x[i]);
//            ssTotal += Math.pow(logY - meanLogY, 2);
//            ssResidual += Math.pow(logY - predictedLogY, 2);
//        }
//
//        double rSquared = 1 - (ssResidual / ssTotal);
//        System.out.printf("\nGoodness of Fit: R² = %.4f\n", rSquared);
    }
}