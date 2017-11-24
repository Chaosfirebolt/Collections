package main.com.collection.map.contract;

import main.com.collection.Collection;
import main.com.collection.ReverseIterable;
import main.com.collection.map.impl.Pair;

/**
 * Created by ChaosFire on 24.11.2017 г.
 */
public interface Dictionary<K, V> extends Collection, Iterable<Pair<K, V>>, ReverseIterable<Pair<K, V>> {

    boolean add(K key, V value);
    boolean remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
    Dictionary<K, V> rangeFrom(K from, boolean fromInclusive);
    Dictionary<K, V> rangeTo(K to, boolean toInclusive);
    Dictionary<K, V> range(K from, boolean fromInclusive, K to, boolean toInclusive);
    K floorKey();
    V floorKeyValue();
    K ceilingKey();
    K ceilingKeyValue();
}