import java.util.Scanner;
import java.lang.Math;

public class AitkensProcess {

    // Define f(x) = 3sin(x) - x - 1/x
    public static double f(double x) {
        return 3*Math.sin(x) - x - 1/x;
    }

    // Define g(x) = 1/(3sin(x) - x) (fixed-point form)
    public static double g(double x) {
        return 1/(3*Math.sin(x) - x);
    }

    // Derivative of g(x) for convergence check
    public static double gPrime(double x) {
        double numerator = -3*Math.cos(x) + 1;
        double denominator = Math.pow(3*Math.sin(x) - x, 2);
        return numerator/denominator;
    }

    // Fixed-Point Iteration Method
    public static void fixedPointIteration(double x0, double e, int N) {
        System.out.println("\nFixed-Point Iteration:");
        System.out.println("Iteration\tx\t\t\tf(x)\t\t\tg'(x)");
        System.out.println("--------------------------------------------------------");

        int step = 1;
        double x1;
        boolean convergent = false;

        do {
            x1 = g(x0);
            System.out.printf("%d\t\t%.10f\t%.10f\t%.10f\n",
                    step, x1, f(x1), gPrime(x0));

            if (Math.abs(f(x1)) <= e) {
                convergent = true;
                break;
            }

            x0 = x1;
            step++;
        } while (step <= N);

        if (convergent) {
            System.out.printf("\nConverged to root: %.10f after %d iterations\n", x1, step);
        } else {
            System.out.println("\nDid not converge within maximum iterations");
        }
    }

    // Aitken's Δ² Process
    public static void aitkensProcess(double x0, double e, int N) {
        System.out.println("\nAitken's Δ² Process:");
        System.out.println("Iteration\tx\t\t\tf(x)");
        System.out.println("----------------------------------------");

        int step = 1;
        double x1, x2, xAitken;
        boolean convergent = false;

        do {
            // Three iterations for Aitken's
            x1 = g(x0);
            x2 = g(x1);

            // Aitken's extrapolation formula
            xAitken = x2 - Math.pow(x2 - x1, 2)/(x2 - 2*x1 + x0);

            System.out.printf("%d\t\t%.10f\t%.10f\n", step, xAitken, f(xAitken));

            if (Math.abs(f(xAitken)) <= e) {
                convergent = true;
                break;
            }

            x0 = xAitken;
            step++;
        } while (step <= N);

        if (convergent) {
            System.out.printf("\nConverged to root: %.10f after %d iterations\n", xAitken, step);
        } else {
            System.out.println("\nDid not converge within maximum iterations");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Solving 3sin(x) = x + 1/x");
        System.out.println("Fixed-point form: x = 1/(3sin(x) - x)");

        double x0 = 0.8; // Initial guess from your example
        System.out.printf("\nChecking convergence condition at x0 = %.1f:\n", x0);
        System.out.printf("g'(%.1f) = %.7f (should be |g'| < 1 for convergence)\n",
                x0, gPrime(x0));

        System.out.print("\nEnter tolerable error (e.g., 1e-8): ");
        double e = scanner.nextDouble();

        System.out.print("Enter maximum iterations: ");
        int N = scanner.nextInt();

        // Run Fixed-Point Iteration
        fixedPointIteration(x0, e, N);

        // Run Aitken's Process
        aitkensProcess(x0, e, N);

        scanner.close();
    }
}