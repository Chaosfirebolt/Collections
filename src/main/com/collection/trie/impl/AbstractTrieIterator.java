package main.com.collection.trie.impl;

import main.com.collection.list.contract.LinkList;
import main.com.collection.list.impl.LinkedList;

import java.util.Map;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
abstract class AbstractTrieIterator<V> {

    private static final int INITIAL = 0;
    private static final String FIRST_KEY = "";

    private int count;
    private int current;
    private LinkList<Pair<V>> pairs;

    AbstractTrieIterator(int count, Node<V> node) {
        this.setCount(count);
        this.setCurrent(INITIAL);
        this.initCollection(node);
    }

    private void initCollection(Node<V> node) {
        this.setPairs(new LinkedList<>());
        this.pairs.add(new Pair<>(FIRST_KEY, node));
    }

    boolean hasNextPair() {
        return this.current < this.count;
    }

    Pair<V> nextPair() {
        Pair<V> pair = this.pairs.removeFirst();
        while (!pair.getNode().isTerminal()) {
            this.processPair(pair);
            pair = this.pairs.removeFirst();
        }
        this.processPair(pair);
        this.current++;
        return pair;
    }

    private void processPair(Pair<V> pair) {
        String key = pair.getKey();
        for (Map.Entry<Character, Node<V>> entry : pair.getNode().getNext().entrySet()) {
            String nextKey = key + entry.getKey();
            this.pairs.add(new Pair<>(nextKey, entry.getValue()));
        }
    }

    private void setCount(int count) {
        this.count = count;
    }

    private void setCurrent(int current) {
        this.current = current;
    }

    private void setPairs(LinkList<Pair<V>> pairs) {
        this.pairs = pairs;
    }
}