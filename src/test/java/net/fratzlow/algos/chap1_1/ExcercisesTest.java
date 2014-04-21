package net.fratzlow.algos.chap1_1;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by frank on 17/03/14.
 * - private functions like scala?
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



    private void numberAsBinary(int n) {
        String s = "";
        for ( int i = n; i > 0; i /= 2 ) {
            System.out.println(i);
            s = ( i % 2 ) + s;
        }

        System.out.println( n + "=" + s + "\n");
    }
}
