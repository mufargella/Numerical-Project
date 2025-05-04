import java.util.Scanner;
import java.lang.Math;

public class NewtonRaphsonCubic {

    // Define the function f(x) = x³ - 2x - 5
    public static double f(double x) {
        return Math.pow(x, 3) - 2*x - 5;
    }

    // Define the derivative f'(x) = 3x² - 2
    public static double fPrime(double x) {
        return 3*Math.pow(x, 2) - 2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Newton-Raphson Method for x³ - 2x - 5 = 0");
        System.out.println("------------------------------------------");

        // Set initial guess to 2 as specified
        double x0 = 2.0;
        System.out.println("Using initial guess x0 = " + x0);

        System.out.print("Enter tolerable error (e.g., 0.0001): ");
        double tolerance = scanner.nextDouble();

        System.out.print("Enter maximum iterations (e.g., 50): ");
        int maxIterations = scanner.nextInt();

        System.out.println("\nIteration\tx_n\t\tf(x_n)\t\tf'(x_n)");
        System.out.println("------------------------------------------");

        int iteration = 0;
        double x1;

        while (iteration < maxIterations) {
            // Check for zero derivative
            if (Math.abs(fPrime(x0)) < 1e-10) {
                System.out.println("\nWarning: Derivative near zero - possible divergence");
                break;
            }

            // Newton-Raphson formula
            x1 = x0 - f(x0)/fPrime(x0);

            // Display current iteration
            System.out.printf("%d\t\t%.8f\t%.8f\t%.8f\n",
                    iteration+1, x1, f(x1), fPrime(x1));

            // Check convergence
            if (Math.abs(f(x1)) < tolerance) {
                System.out.println("\nConverged to solution:");
                System.out.printf("x = %.8f\n", x1);
                System.out.printf("f(x) = %.8f\n", f(x1));
                System.out.println("Iterations: " + (iteration+1));
                scanner.close();
                return;
            }

            x0 = x1;
            iteration++;
        }

        System.out.println("\nMaximum iterations reached without convergence");
        System.out.printf("Last approximation: %.8f\n", x0);
        System.out.printf("f(x) = %.8f\n", f(x0));

        scanner.close();
    }
}