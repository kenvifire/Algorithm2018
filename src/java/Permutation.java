import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

/**
 * Created by kenvi on 2018/1/20.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        String[] inptStrings = StdIn.readAllStrings();

        for (String str : inptStrings) {
            rq.enqueue(str);
        }

        Iterator<String> iterator = rq.iterator();

        while (k-- >  0) {
            System.out.println(iterator.next());
        }


    }
}
