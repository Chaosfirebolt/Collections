package main.com.collection.trie.impl;

/**
 * Created by ChaosFire on 9.9.2017 г.
 */
class Pair<V> {

    private String key;
    private Node<V> node;

    Pair(String key, Node<V> node) {
        this.setKey(key);
        this.setNode(node);
    }

    String getKey() {
        return this.key;
    }

    private void setKey(String key) {
        this.key = key;
    }

    Node<V> getNode() {
        return this.node;
    }

    private void setNode(Node<V> node) {
        this.node = node;
    }
}