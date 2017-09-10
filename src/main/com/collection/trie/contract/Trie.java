package main.com.collection.trie.contract;

import main.com.collection.Collection;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
public interface Trie<V> extends Collection {

    void insert(String key, V value);
    V remove(String key);
    boolean contains(String key);
    V get(String key);
    Iterable<String> getKeysByPrefix(String prefix);
    Iterable<V> getValuesByPrefix(String prefix);
}