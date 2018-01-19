import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by kenvi on 2018/1/13.
 */
public class Percolation2 {

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF vuf;
    private int N;
    private boolean[] states;

    public Percolation2(int N) {
        uf = new WeightedQuickUnionUF(N * N + 2);
        vuf = new WeightedQuickUnionUF(N * N + 2);
        this.N = N;
        states = new boolean[N * N + 2];
        for (int i = 0; i < states.length; i++) {
            states[i] = false;
        }
        // virtual top site
        // for (int i = 1; i <= N; i++) {
        // uf.union(0, i);
        // }

        // virtual buttom site
        // for (int i = N * N; i > N * (N - 1); i--) {
        // uf.union(N * N + 1, i);
        // }

    }

    public void open(int i, int j) {
        if (i > N || i < 1 || j > N || j < 1) {
            throw new IndexOutOfBoundsException();
        }

        if (states[(i - 1) * N + j]) {
            return;
        }
        // open site
        states[(i - 1) * N + j] = true;
        int p, q;
        q = (i - 1) * N + j;

        // up site
        if (i > 1) {
            p = (i - 2) * N + j;
            if (isOpen(i - 1, j)) {
                uf.union(p, q);
                vuf.union(p, q);
            }
        }
        // down site
        if (i < N) {
            p = i * N + j;
            if (isOpen(i + 1, j)) {
                uf.union(p, q);
                vuf.union(p, q);
            }
        }

        // left site
        if (j > 1) {
            p = (i - 1) * N + j - 1;
            if (isOpen(i, j - 1)) {
                uf.union(p, q);
                vuf.union(p, q);
            }

        }

        // right site
        if (j < N) {
            p = (i - 1) * N + j + 1;
            if (isOpen(i, j + 1)) {
                uf.union(p, q);
                vuf.union(p, q);
            }
        }

        // virtual top site
        if (i == 1) {
            uf.union(0, j);
            vuf.union(0, j);
        }

        // virtual buttom site
        if(i==N){
            vuf.union(N*N+1, (i-1)*N+j);
        }

    }

    public boolean isOpen(int i, int j) {
        if (i > N || i < 1 || j > N || j < 1) {
            throw new IndexOutOfBoundsException();
        }
        return states[(i - 1) * N + j];
    }

    public boolean isFull(int i, int j) {
        if (i > N || i < 1 || j > N || j < 1) {
            throw new IndexOutOfBoundsException();
        }
        return uf.connected(0, (i - 1) * N + j);
    }

    public boolean percolates() {
        return vuf.connected(0, N*N+1);
    }

    // public void dumpData(){
    // System.out.println("dumping sites info");
    // for(int i=1;i<=N;i++){
    // for(int j=1;j<=N;j++){
    // if(states[(i-1)*N+j]) System.out.print("X");
    // else System.out.print("_");
    //
    // }
    // System.out.println();
    // }
    //
    // System.out.println("dumping virtual top site  connection info");
    // for(int i=1;i<=N;i++){
    // for (int j = 1; j <= N; j++) {
    // if (uf.connected(0, (i-1)*N+j)) {
    // System.out.println("top-(" + i+","+j+")");
    // }
    // }
    // }
    // System.out.println("dumping virtual buttom site  connection info");
    //
    // for (int i = 1; i<= N; i++) {
    // for (int j = 1; j <= N; j++) {
    // if (uf.connected(N * N + 1, (i-1)*N+j)) {
    // System.out.println("buttom-(" + i + "," + j + ")");
    // }
    // }
    // }
    // }

}
