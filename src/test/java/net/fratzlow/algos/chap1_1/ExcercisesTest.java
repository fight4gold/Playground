package net.fratzlow.algos.chap1_1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by frank on 17/03/14.
 * - private functions like scala?
 *
 * 15, histogram
 * 16, 18, // no op
 * 19 Fibonacci with memorizing
 * 22 mod BinarySearch
 * 24 Math: Euclid algo
 * 25 Math prove via induction // no op
 * 28 BinSearch
 * 33 Math: Matrix lib
 * 34 no op
 * 38 compare algos -> trial
 * 39 trial
 * // usage merge sort? -> difference to BinSearch
 */
public class ExcercisesTest {

    @Test
    public void test_1_1_9() {

        numberAsBinary(0);
        numberAsBinary(1);
        numberAsBinary(2);
        numberAsBinary(3);
        numberAsBinary(4);
        numberAsBinary(5);
        numberAsBinary(18);
    }

    @Test
    public void test_1_1_13() {
        int x = 5;
        int y = 10;
        int[][] matrix = new int[y][x];

        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++)
                matrix[row][col] = col;
        }

        for (int[] line : matrix) System.out.println(Arrays.toString(line));


        // transpose
        for (int row = 0; row < x; row++) {
            int[] vert = new int[y];
            for (int col = 0; col < y; col++) {
                vert[col] = matrix[col][row];
            }
            System.out.println(Arrays.toString(vert));
        }
    }

    /**
     * Make sure to return f(x) = log 2 (N) <= x where x elem Z (pos Int)
     */
    @Test
    public void test_1_1_14() {
        // log2(8)=x -> x=3
        Assert.assertEquals( 3, findLargestPosInt(8) );
        Assert.assertEquals( 3, findLargestPosInt(9));
        Assert.assertEquals( 3, findLargestPosInt(15));
        Assert.assertEquals( 4, findLargestPosInt(16));
    }

    private int findLargestPosInt( int N ) {
        for ( int exponent=0; ;exponent++) {
            int powerOfExp = raise(2, exponent);
            System.out.println(String.format("%d^%d=%d", 2, exponent, powerOfExp));
            if ( powerOfExp > N)
                return --exponent;
        }
    }

    private int raise( int base, int exponent ) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;

        int result = base;
        for (int i=1; i < exponent; i++ )
            result *= base;
        return result;
    }


    private void numberAsBinary(int n) {
        String s = "";
        for ( int i = n; i > 0; i /= 2 ) {
            System.out.println(i);
            s = ( i % 2 ) + s;
        }

        System.out.println( n + "=" + s + "\n");
    }
}
