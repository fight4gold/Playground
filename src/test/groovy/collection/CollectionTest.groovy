package collection

import junit.framework.Assert
import org.junit.Test

/**
 * Some very simplistic implementations of ADT
 *
 * @author ratzlow@gmail.com
 * @since 2015-02-01
 */
class CollectionTest {

    @Test
    void testBiTree() {
        Random rand = new Random()
        int start = 0
        int max = 1_000
        Node treeRoot = new Node()
        def randomInts = []
        (start..max).each {
            def randomInt = rand.nextInt(max + 1)
            randomInts << randomInt
            treeRoot.add( randomInt )
        }

        randomInts.each { assert treeRoot.contains(it) }
        assert !treeRoot.contains( start - 1 )
        assert !treeRoot.contains( max +1 )
        assert !treeRoot.contains( null )
    }

    @Test
    void testLinkedList() {
        List list = new List()
        (1 .. 10).each { list.add(it) }
        (1 .. 10).each { assert list.get(it-1) == it }

        // test empty list
        try {
            List empty = new List()
            empty.get(0)
            Assert.fail()
        } catch ( IndexOutOfBoundsException ex ) {
            Assert.assertTrue(true)
        }

        // test min list
        try {
            List min = new List()
            min.add(1)
            min.get(2)
            Assert.fail()
        } catch ( IndexOutOfBoundsException ex ) {
            Assert.assertTrue(true)
        }
    }
}


class List {
    Link first
    Link last

    def add( def newValue ) {
        if ( first == null ) {
            first = new Link(newValue)
            last = first
        } else {
            def link = new Link( newValue, last )
            last.succ = link
            last = link
        }
    }

    def get( def idx ) {
        if ( first == null ) {
            throw new IndexOutOfBoundsException("Empty list!")
        }

        def i=0
        Link elem = first
        while ( i < idx ) {
            elem = elem.succ
            if ( elem == null ) {
                throw new IndexOutOfBoundsException("No element at $idx")
            }
            i++
        }

        elem.value
    }
}


class Link {
    def value
    Link prev
    Link succ

    Link(value) {
        this.value = value
    }

    Link( value, def prev ) {
        this.value = value
        this.prev = prev
    }
}


class Node {
    def value
    Node leftNode
    Node rightNode

    Node(value) {
        Objects.nonNull(value)
        this.value = value
    }

    // @return <tt>true</tt> if this set did not already contain the specified element
    def add( newValue ) {
        if ( this.value == null )          value = newValue
        else if ( newValue < this.value )  leftNode = addToSide( leftNode, newValue )
        else if ( newValue > this.value )  rightNode = addToSide( rightNode, newValue )
    }

    boolean contains( searchValue ) {
        if      ( value == searchValue )                        true
        else if ( leftNode != null && searchValue < value )     leftNode.contains( searchValue )
        else if ( rightNode != null && searchValue > value )    rightNode.contains( searchValue )
        else                                                    false
    }

    private def addToSide( Node side, def newValue ) {
        if ( side == null ) side = new Node(newValue)
        else                side.add( newValue )

        side
    }
}
