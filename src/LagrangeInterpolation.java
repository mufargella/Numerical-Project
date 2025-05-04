import java.util.Scanner;

public class LagrangeInterpolation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Start
        System.out.println("Lagrange Interpolation Method");

        // Step 2: Read number of data points (n)
        System.out.print("Enter the number of data points: ");
        int n = scanner.nextInt();

        // Step 3: Read data Xi and Yi
        double[] x = new double[n];
        double[] y = new double[n];

        System.out.println("Enter the data points (x, y):");
        for (int i = 0; i < n; i++) {
            System.out.print("x[" + i + "] = ");
            x[i] = scanner.nextDouble();
            System.out.print("y[" + i + "] = ");
            y[i] = scanner.nextDouble();
        }

        // Step 4: Read value of xp to interpolate
        System.out.print("Enter the value of x for interpolation: ");
        double xp = scanner.nextDouble();

        // Step 5: Initialize yp = 0
        double yp = 0;

        // Step 6: Perform interpolation
        for (int i = 0; i < n; i++) {
            double p = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    p *= (xp - x[j]) / (x[i] - x[j]);
                }
            }

            yp += p * y[i];
        }

        // Display the interpolated value
        System.out.println("Interpolated value at x = " + xp + " is y = " + yp);

        scanner.close();
    }

    // Example usage for the given problem
    public static void exampleUsage() {
        // Data from the example
        double[] x = {1, 1.8, 3, 4.5};
        double[] y = {1.41, 2.06, 3.16, 4.6};

        // Points to interpolate
        double x1 = 1.3;
        double x2 = 3.5;

        System.out.println("Example from the problem:");
        System.out.println("Interpolated value at x = " + x1 + " is y = " + interpolate(x, y, x1));
        System.out.println("Interpolated value at x = " + x2 + " is y = " + interpolate(x, y, x2));
    }

    // Helper method for interpolation
    public static double interpolate(double[] x, double[] y, double xp) {
        double yp = 0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            double p = 1;

            for (int j = 0; j < n; j++) {
                if (i != j) {
                    p *= (xp - x[j]) / (x[i] - x[j]);
                }
            }

            yp += p * y[i];
        }

        return yp;
    }
}