import java.util.Scanner;
import java.lang.Math;

public class BisectionMethod {

    // Step 2: Define the function f(x) = x³ - x - 2
    public static double f(double x) {
        return Math.pow(x, 3) - x - 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Start
        System.out.println("Bisection Method for f(x) = x³ - x - 2");
        System.out.println("---------------------------------------");

        // Step 3: Initial guesses (automatically set to 1 and 2 as per example)
        double x0 = 1.0;
        double x1 = 2.0;

        // Verify initial condition f(x0)*f(x1) < 0
        if (f(x0) * f(x1) >= 0) {
            System.out.println("Initial guesses do not bracket the root.");
            System.out.println("f(" + x0 + ") = " + f(x0));
            System.out.println("f(" + x1 + ") = " + f(x1));
            System.exit(0);
        }

        // Step 4: Get tolerable error
        System.out.print("Enter tolerable error (e.g., 0.001): ");
        double e = scanner.nextDouble();

        System.out.println("\nIteration Process:");
        System.out.println("n\tx0\t\tx1\t\tx2\t\tf(x2)");
        System.out.println("--------------------------------------------------");

        double x2;
        int iteration = 0;

        do {
            // Step 5: Calculate new midpoint
            x2 = (x0 + x1) / 2;

            // Display current iteration
            System.out.println(iteration + "\t" +
                    String.format("%.6f", x0) + "\t" +
                    String.format("%.6f", x1) + "\t" +
                    String.format("%.6f", x2) + "\t" +
                    String.format("%.6f", f(x2)));

            // Step 6: Check sub-intervals
            double product = f(x0) * f(x2);

            if (product < 0) {
                // Case a: root is between x0 and x2
                x1 = x2;
            } else if (product > 0) {
                // Case b: root is between x2 and x1
                x0 = x2;
            } else {
                // Case c: x2 is exactly the root
                break;
            }

            iteration++;

            // Step 7: Check convergence
        } while (Math.abs(f(x2)) > e);

        // Step 8: Display root
        System.out.println("\nRoot found at x = " + String.format("%.6f", x2));
        System.out.println("f(root) = " + String.format("%.6f", f(x2)));
        System.out.println("Iterations: " + iteration);

        // Step 9: Stop
        scanner.close();
    }
}