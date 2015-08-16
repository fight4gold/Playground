package net.fratzlow.algos.symboltable;

import java.util.Map;

/**
 * // TODO (FRa) : (FRa) : comment
 *
 * @author ratzlow@gmail.com
 * @since 2015-08-16
 */
class MapDelegator<K,V> implements SymbolTable<K,V> {

    final Map<K,V> map;

    public MapDelegator( Map<K, V> map ) {
        this.map = map;
    }

    @Override
    public void put(K key, V value) { map.put(key, value ); }

    @Override
    public V get(K key) { return map.get( key ); }

    @Override
    public void delete(K key) { map.remove(key); }

    @Override
    public boolean contains(K key) { return map.containsKey( key ); }

    @Override
    public boolean isEmpty() { return map.isEmpty(); }

    @Override
    public int size() { return map.size(); }

    @Override
    public Iterable<K> keys() { return map.keySet(); }
}
