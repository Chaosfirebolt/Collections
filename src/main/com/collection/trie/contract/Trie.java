package main.com.collection.trie.contract;

import main.com.collection.Collection;

import java.util.Iterator;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
public interface Trie<V> extends Collection, Iterable<V> {

    void insert(String key, V value);
    V remove(String key);
    boolean contains(String key);
    V get(String key);
    Iterable<String> getKeysByPrefix(String prefix);
    Iterable<V> getValuesByPrefix(String prefix);
    Iterator<String> keyIterator();
}