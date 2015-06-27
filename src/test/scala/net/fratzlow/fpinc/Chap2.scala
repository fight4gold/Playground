package net.fratzlow.fpinc

import scala.annotation.tailrec

/**
 * @author ratzlow@gmail.com
 * @since 2015-04-20
 */
object Chap2 {

    def fib(n: Long) : Long = {

        @tailrec
        def fibInner( i: Long, sum: Long): Long = if (i > n) sum else fibInner( i+1, sum + i)

        fibInner( 0, 0 )
    }


    def isSorted[A]( ar: Array[A], ordered: (A, A) => Boolean) : Boolean = {

        @tailrec
        def sorted( maxIdx: Int ) : Boolean = {
            ( maxIdx == 0 ) || (ordered( ar(maxIdx - 1), ar(maxIdx) ) && sorted( maxIdx - 1 ))
        }

        ar.length == 0 || sorted( ar.length - 1 )
    }


    def curry[A,B,C] (f: (A,B) => C) : (A => B => C) =
        (a: A) => (b: B) => f(a, b)

    def uncurry[A,B,C] (f: A => B => C) : (A,B) => C =
        (a: A, b: B) => f(a)(b)

    def compose[A,B,C] (f: B => C, g: A => B) : A => C =
        (a: A) => f(g(a))
}
