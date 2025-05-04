public class ModifiedRomberg {

    public static void main(String[] args) {
        double a = 1.0;
        double b = 2.0;
        int maxN = 4;  // Maximum column n (matches the formula's n)

        // Romberg matrix: I[k][n] where k >= n (1-based indexing)
        double[][] I = new double[maxN + 1][maxN + 1];

        // Step 1: Compute trapezoidal estimates for n=1 (first column)
        System.out.println("Trapezoidal Estimates (n=1):");
        for (int k = 1; k <= maxN; k++) {
            int m = (int) Math.pow(2, k - 1);  // Number of intervals = 2^(k-1)
            I[k][1] = trapezoidal(m, a, b);
            System.out.printf("I_%d,1 = %.8f (h=%.5f)\n", k, I[k][1], (b-a)/m);
        }
        System.out.println();

        // Step 2: Compute columns n=2 to maxN using the corrected formula
        for (int n = 2; n <= maxN; n++) {
            System.out.printf("Computing column n=%d:\n", n);
            for (int k = n; k <= maxN; k++) {
                double denominator = Math.pow(4, n-1) - 1;
                double delta = I[k][n-1] - I[k-1][n-1];
                I[k][n] = I[k][n-1] + delta / denominator;

                // Detailed output to match the formula
                System.out.printf(
                        "I_%d,%d = %.8f + (1/(4^%d -1))(%.8f) = %.8f\n",
                        k, n, I[k][n-1], n-1, delta, I[k][n]
                );
            }
            System.out.println();
        }

        // Final Romberg table and result
        System.out.println("Complete Romberg Table:");
        printTable(I, maxN);
        System.out.printf("\nln(2) ≈ I_4,4 = %.8f\n", I[maxN][maxN]);
    }

    // Trapezoidal rule for m intervals
    private static double trapezoidal(int m, double a, double b) {
        double h = (b - a) / m;
        double sum = 0.5 * (f(a) + f(b));
        for (int i = 1; i < m; i++) {
            sum += f(a + i * h);
        }
        return h * sum;
    }

    // Integrand f(x) = 1/x
    private static double f(double x) {
        return 1.0 / x;
    }

    // Print the Romberg table
    private static void printTable(double[][] I, int maxN) {
        for (int k = 1; k <= maxN; k++) {
            for (int n = 1; n <= k; n++) {
                System.out.printf("I_%d,%d = %-10.8f  ", k, n, I[k][n]);
            }
            System.out.println();
        }
    }
}