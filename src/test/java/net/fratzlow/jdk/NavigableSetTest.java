package net.fratzlow.jdk;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by frank on 08/02/14.
 */
public class NavigableSetTest {
    private NavigableSet<Integer> set;

    @Before
    public void init() {
        set = new TreeSet<Integer>();
        set.addAll(Arrays.asList(1, 2, 3, 4, 5));
    }




    /**
     * elements are greater than or equal to "fromElement"
     */
    @Test
    public void testSubSet() {
        Assert.assertEquals(set(1,2,3,4,5), set.subSet(0, 99) );

        // per default head is excluded
        Assert.assertEquals(set(1,2,3,4), set.subSet(1, 5) );

        // include head with special indicators
        Assert.assertEquals(set(1,2,3,4,5), set.subSet(1, true, 5, true) );
    }

    
    /**
     * elements are greater than or equal to "fromElement"
     */
    @Test
    public void testTailSet() {
        Assert.assertEquals(set(1,2,3,4,5), set.tailSet(0) );
        Assert.assertEquals(set(1,2,3,4,5), set.tailSet(1) );

        Assert.assertEquals(set(5), set.tailSet(5) );
        Assert.assertEquals(set(), set.tailSet(6) );
    }

    /**
     * elements are strictly less than "toElement"
     */
    @Test
    public void testHeadSet() {
        Assert.assertEquals(0, set.headSet(0).size() );
        Assert.assertEquals(0, set.headSet(1).size() );
        Assert.assertEquals(set(1), set.headSet(2) );

        Assert.assertEquals(set(1, 2,3,4 ), set.headSet(5) );
        Assert.assertEquals(set(1, 2,3,4,5 ), set.headSet(99) );
    }

    @Test
    public void testHigher() {
        Assert.assertEquals(Integer.valueOf(1), set.higher(0) );
        Assert.assertEquals(Integer.valueOf(2), set.higher(1) );

        Assert.assertEquals(Integer.valueOf(5), set.higher(4) );
        Assert.assertEquals(null, set.higher(5) );
    }

    @Test
    public void testLower() {
        Assert.assertEquals(null, set.lower(1) );
        Assert.assertEquals(null, set.lower(0) );
        Assert.assertEquals(Integer.valueOf(1), set.lower(2) );

        Assert.assertEquals(Integer.valueOf(4), set.lower(5) );
        Assert.assertEquals(Integer.valueOf(5), set.lower(6) );
        Assert.assertEquals(Integer.valueOf(5), set.lower(99) );
    }

    /**
     * lessThanEqual
     */
    @Test
    public void testFloor() {
        Assert.assertEquals(Integer.valueOf(1), set.floor(1) );
        Assert.assertEquals(null, set.floor(0) );
        Assert.assertEquals(null, set.floor(-99) );

        Assert.assertEquals(Integer.valueOf(3), set.floor(3) );

        Assert.assertEquals(Integer.valueOf(5), set.floor(5) );
        Assert.assertEquals(Integer.valueOf(5), set.floor(6) );
        Assert.assertEquals(Integer.valueOf(5), set.floor(99) );
    }
    
    /**
     * greaterThanEqual
     */
    @Test
    public void testCeiling() {
        Assert.assertEquals(Integer.valueOf(1), set.ceiling(1) );
        Assert.assertEquals(Integer.valueOf(1), set.ceiling(0) );
        Assert.assertEquals(Integer.valueOf(1), set.ceiling(-99) );

        Assert.assertEquals(Integer.valueOf(3), set.ceiling(3) );

        Assert.assertEquals(Integer.valueOf(5), set.ceiling(5) );
        Assert.assertEquals(null, set.ceiling(6) );
    }


    @Test
    public void testFirst_Last() {
        Assert.assertEquals(Integer.valueOf(1), set.first());
        Assert.assertEquals(Integer.valueOf(5), set.last());
        Assert.assertEquals( 5, set.size() );
    }

    @Test
    public void testPollFirst() {
        Assert.assertEquals(Integer.valueOf(1), set.pollFirst());
        Assert.assertEquals(4, set.size());
        Assert.assertFalse(set.contains(Integer.valueOf(1)));
    }


    private TreeSet<Integer> set( Integer ... ints ) {
        return new TreeSet<Integer>( Arrays.asList( ints ));
    }
}
