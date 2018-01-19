
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by kenvi on 2018/1/12.
 */
public class Percolation {

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF vuf;
    private int size;
    private int openSitesCnt = 0;
    private boolean[][] sites;

    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        if(n <= 0)  throw new IllegalArgumentException();
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.vuf = new WeightedQuickUnionUF(n * n + 2);
        this.size = n;
        sites = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
        openSitesCnt = 0;
    }
    public void open(int row, int col) {    // open site (row, col) if it is not open already
        if (row <= 0 || col <= 0 || row > this.size || col > this.size) {
            throw new IllegalArgumentException(String.format("Invalid input (%d,%d)", row, col));
        }
        if (!isOpen(row, col)) {
            if (row - 1 > 0 && isOpen(row-1, col)) {
                this.uf.union((row -1) * size + col, (row - 2) * size + col);
                this.vuf.union((row -1) * size + col, (row - 2) * size + col);
            }

            if (row + 1 <= size && isOpen(row+1, col)) {
                this.uf.union((row-1) * size + col, row  * size + col);
                this.vuf.union((row-1) * size + col, row  * size + col);
            }

            if (col - 1 > 0 && isOpen(row, col - 1)) {
                this.uf.union((row - 1)* size + col, (row - 1) * size + col - 1);
                this.vuf.union((row - 1)* size + col, (row - 1) * size + col - 1);
            }

            if (col + 1 <= size && isOpen(row, col + 1)) {
                this.uf.union((row - 1) * size + col, (row - 1) * size + col + 1);
                this.vuf.union((row - 1) * size + col, (row - 1) * size + col + 1);
            }

            // virtual top
            if (row == 1) {
                this.uf.union((row - 1) * size + col, 0);
                this.vuf.union((row - 1) * size + col, 0);
            }

            //virtual bottom
            if (row == size) {
                this.vuf.union((row - 1) * size + col, size * size + 1);
            }
            openSitesCnt++;
            sites[row-1][col-1] = true;
        }


    }
    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (row <= 0 || col <= 0 || row > this.size || col > this.size) {
            throw new IllegalArgumentException(String.format("Invalid input (%d,%d)", row, col));
        }

        return sites[row-1][col-1];
    }
    public boolean isFull(int row, int col) { // is site (row, col) full?
        if (row <= 0 || col <= 0 || row > this.size || col > this.size) {
            throw new IllegalArgumentException(String.format("Invalid input (%d,%d)", row, col));
        }
        return uf.connected(0, (row-1) * size + col);

    }
    public int numberOfOpenSites() {      // number of open sites
        return openSitesCnt;

    }
    public boolean percolates() {              // does the system percolate?
        return vuf.connected(0, size * size + 1);
    }

    public static void main(String[] args) {   // test client (optional)
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        System.out.println(percolation.isOpen(1, 1));
        System.out.println(percolation.openSitesCnt);
        System.out.println(percolation.isOpen(1, 1));
        System.out.println(percolation.percolates());
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        System.out.println(percolation.openSitesCnt);
        System.out.println(percolation.percolates());
    }


}
