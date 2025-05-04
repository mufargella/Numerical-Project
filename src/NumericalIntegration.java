import java.util.Scanner;
import java.lang.Math;

public class NumericalIntegration {

    // CORRECTED function to integrate: f(x) = 1/(9 + x²)
    public static double f(double x) {
        return 1.0 / (9.0 + x*x);
    }

    // Exact integral value (from calculus)
    public static double exactIntegral() {
        return (Math.atan(3.0/3.0) / 3.0); // ∫(1/(9+x²))dx from 0 to 3 = [arctan(x/3)/3]
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = 0.0;      // lower limit
        double b = 3.0;      // upper limit
        int n = 6;           // number of subintervals
        double h = (b - a)/n; // step size

        System.out.println("Numerical Integration of f(x) = 1/(9 + x²) from 0 to 3");
        System.out.println("Number of subintervals (n): " + n);
        System.out.println("Step size (h): " + h);

        // Create and display the table of x and f(x) values
        System.out.println("\nTable of x and f(x) values:");
        System.out.println("----------------------------");
        System.out.println("  x  |  f(x)");
        System.out.println("-----|--------");

        double[] x = new double[n+1];
        double[] y = new double[n+1];

        for (int i = 0; i <= n; i++) {
            x[i] = a + i * h;
            y[i] = f(x[i]);
            System.out.printf("%.1f | %.6f%n", x[i], y[i]);
        }

        // Trapezoidal Rule calculation
        double trapezoidalSum = y[0] + y[n];
        for (int i = 1; i < n; i++) {
            trapezoidalSum += 2 * y[i];
        }
        double trapezoidalResult = (h / 2) * trapezoidalSum;

        // Simpson's Rule calculation
        double simpsonSum = y[0] + y[n];
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) { // odd index
                simpsonSum += 4 * y[i];
            } else { // even index
                simpsonSum += 2 * y[i];
            }
        }
        double simpsonResult = (h / 3) * simpsonSum;

        // Exact integral value
        double exact = exactIntegral();

        // Calculate absolute errors
        double trapezoidalError = Math.abs(exact - trapezoidalResult);
        double simpsonError = Math.abs(exact - simpsonResult);

        // Approximate π from the integral result
        double piApproximation = 12 * simpsonResult;

        // Display results
        System.out.println("\nResults:");
        System.out.println("----------------------------");
        System.out.printf("Exact integral value (I_exact): %.10f%n", exact);
        System.out.printf("Trapezoidal Rule result (I_t):  %.10f%n", trapezoidalResult);
        System.out.printf("Simpson's Rule result (I_s):    %.10f%n", simpsonResult);
        System.out.printf("Absolute error (Trapezoidal):   %.10f%n", trapezoidalError);
        System.out.printf("Absolute error (Simpson):       %.10f%n", simpsonError);
        System.out.printf("Approximation of π:             %.10f%n", piApproximation);

        scanner.close();
    }
}