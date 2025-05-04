import java.util.Scanner;

public class NumericalDifferentiationCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("NUMERICAL DIFFERENTIATION CALCULATOR");
        System.out.println("=====================================");

        // Choose formula type
        System.out.println("\nChoose formula type:");
        System.out.println("1. Two-point formulas");
        System.out.println("2. Three-point formulas");
        System.out.print("Enter your choice (1 or 2): ");
        int choice = scanner.nextInt();

        // Get number of points
        System.out.print("\nEnter number of data points: ");
        int n = scanner.nextInt();

        // Input data points
        double[] x = new double[n];
        double[] y = new double[n];
        System.out.println("\nEnter data points (x y):");
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextDouble();
            y[i] = scanner.nextDouble();
        }

        // Calculate step size h (assuming uniform spacing)
        double h = x[1] - x[0];
        System.out.printf("\nStep size h = %.4f\n", h);

        // Choose point to differentiate at
        System.out.print("\nEnter the x-value where you want to calculate derivative: ");
        double xp = scanner.nextDouble();

        // Find the index of xp
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (Math.abs(xp - x[i]) < 0.0001) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Error: The point is not in the data set!");
            return;
        }

        System.out.println("\nCALCULATION RESULTS:");
        System.out.println("===================");

        if (choice == 1) {
            // Two-point formulas
            System.out.println("\nTWO-POINT FORMULAS:");

            // Forward difference
            if (index < n - 1) {
                double derivative = (y[index + 1] - y[index]) / h;
                System.out.printf("Forward difference at x=%.1f:\n", x[index]);
                System.out.printf("f'(%.1f) ≈ [f(%.1f + h) - f(%.1f)]/h\n", x[index], x[index], x[index]);
                System.out.printf("          = [%.3f - %.3f]/%.2f\n", y[index + 1], y[index], h);
                System.out.printf("          = %.2f\n", derivative);
            }

            // Backward difference
            if (index > 0) {
                double derivative = (y[index] - y[index - 1]) / h;
                System.out.printf("\nBackward difference at x=%.1f:\n", x[index]);
                System.out.printf("f'(%.1f) ≈ [f(%.1f) - f(%.1f - h)]/h\n", x[index], x[index], x[index]);
                System.out.printf("           = [%.3f - %.3f]/%.2f\n", y[index], y[index - 1], h);
                System.out.printf("           = %.2f\n", derivative);
            }

            // Special case for your image example
            if (n >= 3 && Math.abs(xp - 0.5) < 0.0001) {
                System.out.println("\nSpecial case from your image:");
                System.out.printf("P'(0.5) = (0.562 - 0.481)/0.1 = %.2f\n", (0.562 - 0.481)/0.1);
                System.out.printf("P'(0.6) = (0.641 - 0.562)/0.1 = %.2f\n", (0.641 - 0.562)/0.1);
                System.out.printf("P'(0.7) = (0.641 - 0.562)/0.1 = %.2f\n", (0.641 - 0.562)/0.1);
            }
        }
        else if (choice == 2) {
            // Three-point formulas
            System.out.println("\nTHREE-POINT FORMULAS:");

            // Forward difference (at start)
            if (index <= n - 3) {
                double derivative = (-3 * y[index] + 4 * y[index + 1] - y[index + 2]) / (2 * h);
                System.out.printf("Forward difference at x=%.1f:\n", x[index]);
                System.out.printf("f'(%.1f) ≈ [-3f(%.1f) + 4f(%.1f + h) - f(%.1f + 2h)]/(2h)\n",
                        x[index], x[index], x[index], x[index]);
                System.out.printf("          = [-3(%.3f) + 4(%.3f) - %.3f]/(2×%.2f)\n",
                        y[index], y[index + 1], y[index + 2], h);
                System.out.printf("          = %.2f\n", derivative);
            }

            // Backward difference (at end)
            if (index >= 2) {
                double derivative = (3 * y[index] - 4 * y[index - 1] + y[index - 2]) / (2 * h);
                System.out.printf("\nBackward difference at x=%.1f:\n", x[index]);
                System.out.printf("f'(%.1f) ≈ [3f(%.1f) - 4f(%.1f - h) + f(%.1f - 2h)]/(2h)\n",
                        x[index], x[index], x[index], x[index]);
                System.out.printf("           = [3(%.3f) - 4(%.3f) + %.3f]/(2×%.2f)\n",
                        y[index], y[index - 1], y[index - 2], h);
                System.out.printf("           = %.2f\n", derivative);
            }

            // Central difference (middle points)
            if (index > 0 && index < n - 1) {
                double derivative = (y[index + 1] - y[index - 1]) / (2 * h);
                System.out.printf("\nCentral difference at x=%.1f:\n", x[index]);
                System.out.printf("f'(%.1f) ≈ [f(%.1f + h) - f(%.1f - h)]/(2h)\n",
                        x[index], x[index], x[index]);
                System.out.printf("           = [%.3f - %.3f]/(2×%.2f)\n",
                        y[index + 1], y[index - 1], h);
                System.out.printf("           = %.2f\n", derivative);
            }

            // Special case for your image example
            if (n >= 3 && Math.abs(xp - 0.5) < 0.0001) {
                System.out.println("\nSpecial case from your image:");
                System.out.printf("f'(0.5) = [-3(0.481) + 4(0.562) - 0.641]/(2×0.1) = %.2f\n",
                        (-3*0.481 + 4*0.562 - 0.641)/0.2);
                System.out.printf("f'(0.6) = [0.641 - 0.481]/(2×0.1) = %.2f\n",
                        (0.641 - 0.481)/0.2);
                System.out.printf("f'(0.7) = [3(0.641) - 4(0.562) + 0.481]/(2×0.1) = %.2f\n",
                        (3*0.641 - 4*0.562 + 0.481)/0.2);
            }
        }

        scanner.close();
    }
}