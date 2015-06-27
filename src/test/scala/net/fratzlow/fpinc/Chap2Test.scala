package net.fratzlow.fpinc

import net.fratzlow.fpinc.Chap2.isSorted
import org.scalatest.{Matchers, FlatSpec}

/**
 * Exercises for Functional Programming in Scala
 *
 * @author ratzlow@gmail.com
 * @since 2015-04-20
 */
class Chap2Test extends FlatSpec with Matchers {

    "fibonacci" should " add 2 subsequent numbers" in {
        val num = 3
        assert ( 0 + 1 + 2 + 3 === Chap2.fib(num))
    }

    "fib test" should "finish in 1.5sec" in {
        val num = 5000000000L
        val start = System.currentTimeMillis()
        val sum = Chap2.fib( num )
        printf("Sum is %d and duration is %d ms", sum, System.currentTimeMillis() - start)
    }

    "recursive test of arrays" should "work for Integers" in {
        assert ( isSorted( Array(1,2,3,4,5,6), (x: Int, y: Int) => x <= y))
        assert ( isSorted( Array(1,2,3,4,5,5), (x: Int, y: Int) => x <= y))
        assert ( isSorted( Array(1), (x: Int, y: Int) => x <= y))
        assert ( isSorted( Array(), (x: Int, y: Int) => x <= y))
        assert ( false === isSorted( Array(1,2,3,2), (x: Int, y: Int) => x <= y))
    }
}
