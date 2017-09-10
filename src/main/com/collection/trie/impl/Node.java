package main.com.collection.trie.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
class Node<V> {

    private V value;
    private boolean isTerminal;
    private Map<Character, Node<V>> next;

    private Node(V value, boolean isTerminal, Map<Character, Node<V>> next) {
        this.setValue(value);
        this.setTerminal(isTerminal);
        this.setNext(next);
    }

    Node(boolean isTerminal) {
        this(null, isTerminal, new HashMap<>());
    }

    Node(V value) {
        this(value, true, new HashMap<>());
    }

    V getValue() {
        return this.value;
    }

    void setValue(V value) {
        this.value = value;
    }

    boolean isTerminal() {
        return this.isTerminal;
    }

    void setTerminal(boolean terminal) {
        isTerminal = terminal;
    }

    Map<Character, Node<V>> getNext() {
        return this.next;
    }

    private void setNext(Map<Character, Node<V>> next) {
        this.next = next;
    }
}