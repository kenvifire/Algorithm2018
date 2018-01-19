import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by kenvi on 2018/1/13.
 */
public class PercolationTest extends TestCase {

    @Test
    public void testInput6() throws FileNotFoundException {
//        Percolation per = generate("input6");
//        assertEquals(false, per.isFull(1, 1));
    }

    private Percolation generate(String fileName) throws FileNotFoundException{

        InputStream in = this.getClass().getClassLoader().getResourceAsStream("percolationtest/" + fileName);
        Scanner scanner = new Scanner(in);
        int n = scanner.nextInt();
        Percolation per = new Percolation(n);

        while(scanner.hasNextInt()){
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            per.open(p, q);
        }
        return per;
    }

    @Test
    public void testInput8() throws FileNotFoundException{
        Percolation per = generate("input8");
        assertEquals(false, per.isFull(1, 1));
    }

    @Test
    public void testInput8_no() throws FileNotFoundException{
        Percolation per = generate("input8-no");
        assertEquals(false, per.isFull(1, 1));
    }
    @Test
    public void testInput10_no() throws FileNotFoundException{
        Percolation per = generate("input10-no");
        assertEquals(false, per.isFull(1, 1));
    }

    @Test
    public void testInputGreeting57() throws FileNotFoundException{
        Percolation per = generate("greeting57");
        //per.dumpData();
        assertEquals(true, per.isFull(1, 1));

    }

    @Test
    public void testInputHeart25() throws FileNotFoundException{
        Percolation per = generate("heart25");
        assertEquals(false, per.isFull(1, 1));
    }

    @Test
    public void testInput1() throws FileNotFoundException{
        Percolation per = generate("input1");
        //	per.dumpData();
        assertEquals(true, per.isFull(1, 1));
    }

    @Test
    public void testInput20() throws FileNotFoundException{
        Percolation per = generate("input20");
        assertEquals(false, per.isFull(18, 1));
    }

    @Test
    public void testInput10() throws FileNotFoundException{
        Percolation per = generate("input10");
//        	per.dumpData();
        assertEquals(false, per.isFull(10, 8));
    }


}
