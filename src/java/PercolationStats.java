
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * Created by kenvi on 2018/1/12.
 */
public class PercolationStats {
    private double[] results;
    private int trials;
    private double meanValue;
    private double stdDev;

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if( n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int row, col;
            while (!percolation.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                percolation.open(row, col);
            }
            results[i] = percolation.numberOfOpenSites()  / (double)(n * n);
        }

        this.meanValue = StdStats.mean(results);
        this.stdDev = StdStats.stddev(results);
    }
    public double mean() {                          // sample mean of tests.percolationtest threshold
        return this.meanValue;
    }
    public double stddev() {                        // sample standard deviation of tests.percolationtest threshold
        return this.stdDev;
    }
    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return mean() - 1.96 * stddev()/Math.sqrt(this.trials);
    }
    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev()/Math.sqrt(this.trials);
    }

    public static void main(String[] args) {        // test client (described below)
        int n = StdIn.readInt();
        int trials = StdIn.readInt();

        PercolationStats ps = new PercolationStats(n, trials);

        StdOut.printf("mean                    = %f\n", ps.mean());
        StdOut.printf("stddev                  = %f\n", ps.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", ps.confidenceLo(), ps.confidenceHi());

    }
}
