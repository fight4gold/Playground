package net.fratzlow.algos.symboltable;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2015-08-16
 */
interface SymbolTable<K, V> {
    void put( K key, V value );
    V get( K key );
    void delete( K key );
    boolean contains(K key );
    boolean isEmpty();
    int size();
    Iterable<K> keys();
}
