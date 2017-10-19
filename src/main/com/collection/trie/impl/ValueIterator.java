package main.com.collection.trie.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
class ValueIterator<V> extends AbstractTrieIterator<V> implements Iterator<V> {

    ValueIterator(int count, Node<V> node, int expectedModCount, MapTrie<V> trie) {
        super(count, node, expectedModCount, trie);
    }

    @Override
    public boolean hasNext() {
        return super.hasNextPair();
    }

    @Override
    public V next() {
        return super.nextPair().getNode().getValue();
    }

    @Override
    public void remove() {
        super.removePair();
    }
}