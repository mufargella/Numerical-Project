import java.util.Scanner;
import java.lang.Math;

/**
 * Fixed Point Iteration Method Project
 * Solves nonlinear equations of the form f(x) = 0
 * by rewriting as x = g(x) and iterating
 */
public class Fixed_Point_method{

    // Function to solve: f(x) = 0
    public static double f(double x) {
        // Example function: x³ - x - 1 = 0
        // Can be replaced with any function
        return Math.pow(x, 3) - x - 1;
    }

    // Rearranged function: x = g(x)
    public static double g(double x) {
        // For f(x) = x³ - x - 1 = 0
        // Possible rearrangement: x = (x + 1)^(1/3)
        return Math.cbrt(x + 1);
    }

    // Derivative of g(x) for convergence check
    public static double gDerivative(double x) {
        return 1 / (3 * Math.pow(x + 1, 2.0/3.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Start
        System.out.println("Fixed Point Iteration Method");
        System.out.println("----------------------------");

        // Step 2 & 3: Functions already defined above

        // Step 4: Get inputs
        System.out.print("Enter initial guess (x0): ");
        double x0 = scanner.nextDouble();

        // Check convergence condition |g'(x)| < 1
        if (Math.abs(gDerivative(x0)) >= 1) {
            System.out.println("Warning: |g'(x0)| >= 1, convergence not guaranteed");
        }

        System.out.print("Enter tolerable error: ");
        double e = scanner.nextDouble();

        System.out.print("Enter maximum iterations (N): ");
        int N = scanner.nextInt();

        // Step 5: Initialize counter
        int step = 1;
        double x1;

        System.out.println("\nIteration Process:");
        System.out.println("Step\tx_n\t\tf(x_n)");
        System.out.println("--------------------------");

        // Iteration loop
        do {
            // Step 6: Calculate next approximation
            x1 = g(x0);

            // Display current iteration
            System.out.printf("%d\t%.6f\t%.6f\n", step, x1, f(x1));

            // Step 7: Increment counter
            step++;

            // Step 8: Check maximum iterations
            if (step > N) {
                System.out.println("\nNot Convergent within given iterations");
                break;
            }

            // Step 9: Update x0 for next iteration
            x0 = x1;

            // Step 10: Check error condition
        } while (Math.abs(f(x1)) > e);

        // Step 11: Display root if converged
        if (step <= N) {
            System.out.println("\nRoot found: " + x1);
            System.out.println("f(root) = " + f(x1));
            System.out.println("Iterations: " + (step-1));
        }

        // Step 12: Stop
        scanner.close();
        System.out.println("\nAlgorithm completed");
    }
}