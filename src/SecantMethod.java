import java.util.Scanner;
import java.lang.Math;

public class SecantMethod {

    // Define the function f(x) = x - 0.8 - 0.2*sin(x)
    public static double function(double x) {
        return x - 0.8 - 0.2 * Math.sin(x);
    }

    public static void secant(double x0, double x1, double e, int N) {
        System.out.println("\nSecant Method Implementation for f(x) = x - 0.8 - 0.2*sin(x)");
        System.out.println("Iteration\tx0\t\tx1\t\t\tx2\t\t\t\tf(x2)");
        System.out.println("--------------------------------------------------------------");

        int i = 1;
        double x2;

        do {
            if (function(x0) == function(x1)) {
                System.out.println("Mathematical Error: Division by zero");
                return;
            }

            // Calculate the next approximation
            x2 = x1 - (x1 - x0) * function(x1) / (function(x1) - function(x0));

            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f\t%.6f\n",
                    i, x0, x1, x2, function(x2));

            // Update values for next iteration
            x0 = x1;
            x1 = x2;
            i++;

            if (i > N) {
                System.out.println("Not Convergent after " + N + " iterations");
                return;
            }

        } while (Math.abs(function(x2)) > e);

        System.out.println("\nThe root is: " + x2);
        System.out.println("Found after " + (i-1) + " iterations");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Solving f(x) = x - 0.8 - 0.2*sin(x) = 0 using Secant Method");

        // Initial guesses (using the example values)
        double x0 = 0.0;
        double x1 = Math.PI/2;

        // Tolerable error
        double e = 0.0001;

        // Maximum iterations
        int N = 20;

        System.out.println("Using default values:");
        System.out.println("x0 = " + x0);
        System.out.println("x1 = " + x1);
        System.out.println("Tolerable error = " + e);
        System.out.println("Maximum iterations = " + N);

        // Call secant method
        secant(x0, x1, e, N);

        scanner.close();
    }
}