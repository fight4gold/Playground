package net.fratzlow.fpinc

import scala.annotation.tailrec

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2015-06-23
 */
object Chap3 {

    def tail[A]( xs: List[A]) : List[A] = xs match {
        case Nil    => throw new UnsupportedOperationException
        case h :: t => t
    }

    def setHead[A]( xs: List[A], first: A) : List[A] = xs match {
        case Nil  => throw new UnsupportedOperationException
        case h::t => first :: t
    }

    @tailrec
    def drop[A]( xs: List[A], n: Int) : List[A] = {
        if ( n == 0 ) xs
        else xs match {
            case Nil    => Nil
            case h::t  => drop( t, n - 1)
        }
    }

    @tailrec
    def dropWhile[A]( xs: List[A], predicate: A => Boolean ) : List[A] = xs match {
        case h :: t if predicate.apply(h) => dropWhile( t, predicate )
        case _                            => xs
    }
}
