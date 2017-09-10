package main.com.collection.trie.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
class KeyIterator<V> extends AbstractTrieIterator<V> implements Iterator<String> {

    KeyIterator(int count, Node<V> node) {
        super(count, node);
    }

    @Override
    public boolean hasNext() {
        return super.hasNextPair();
    }

    @Override
    public String next() {
        return super.nextPair().getKey();
    }
}