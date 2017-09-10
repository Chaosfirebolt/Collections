package main.com.collection.trie.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
class ValueIterator<V> extends AbstractTrieIterator<V> implements Iterator<V> {

    ValueIterator(int count, Node<V> node) {
        super(count, node);
    }

    @Override
    public boolean hasNext() {
        return super.hasNextPair();
    }

    @Override
    public V next() {
        return super.nextPair().getNode().getValue();
    }
}