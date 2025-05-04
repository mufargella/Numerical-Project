import java.util.Scanner;

public class GaussSeidelMethod {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Given system of equations:
        // 5x₁ - x₂ + x₃ = 10
        // 2x₁ + 8x₂ - x₃ = 11
        // -x₁ + x₂ + 4x₃ = 3

        // Check diagonal dominance
        // |5| > |-1| + |1| → 5 > 2 ✔
        // |8| > |2| + |-1| → 8 > 3 ✔
        // |4| > |-1| + |1| → 4 > 2 ✔
        // System is diagonally dominant

        System.out.println("Gauss-Seidel Iteration Method for the system:");
        System.out.println("5x₁ - x₂ + x₃ = 10");
        System.out.println("2x₁ + 8x₂ - x₃ = 11");
        System.out.println("-x₁ + x₂ + 4x₃ = 3");

        // Read tolerable error
        System.out.print("Enter tolerable error (e.g., 0.001): ");
        double e = scanner.nextDouble();

        // Maximum iterations to prevent infinite loop
        System.out.print("Enter maximum number of iterations: ");
        int maxIterations = scanner.nextInt();

        // Initial guesses (all zeros by default)
        double x0 = 0, y0 = 0, z0 = 0;
        double x1, y1, z1;

        System.out.println("\nIteration\tx₁\t\tx₂\t\tx₃");
        System.out.println("--------------------------------------------------");

        int iteration = 1;
        while (iteration <= maxIterations) {
            // Calculate new values using immediately updated values (difference from Jacobi)
            x1 = (10 + y0 - z0) / 5;
            y1 = (11 - 2 * x1 + z0) / 8;  // Uses x1 instead of x0
            z1 = (3 + x1 - y1) / 4;      // Uses x1 and y1

            // Print current iteration
            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f\n", iteration, x1, y1, z1);

            // Check for convergence
            if (Math.abs(x0 - x1) < e &&
                    Math.abs(y0 - y1) < e &&
                    Math.abs(z0 - z1) < e) {
                System.out.println("\nConverged after " + iteration + " iterations");
                System.out.printf("Solution: x₁ = %.6f, x₂ = %.6f, x₃ = %.6f\n", x1, y1, z1);
                scanner.close();
                return;
            }

            // Update for next iteration
            x0 = x1;
            y0 = y1;
            z0 = z1;
            iteration++;
        }

        System.out.println("\nDid not converge within " + maxIterations + " iterations");
        scanner.close();
    }
}