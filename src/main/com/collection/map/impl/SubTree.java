package main.com.collection.map.impl;

import java.util.Comparator;

/**
 * Created by ChaosFire on 22.11.2017 г.
 */
class SubTree<K extends Comparable<K>, V> extends AVLTree<K, V> {

    private Node<Pair<K, V>> minNode;
    private Node<Pair<K, V>> maxNode;

    SubTree(Node<Pair<K, V>> root, Comparator<K> comparator, boolean replaceEqual, ModCount modCount,
            Node<Pair<K, V>> minNode, Node<Pair<K, V>> maxNode) {
        super(root, comparator, replaceEqual, new ModCount(modCount));
        this.setBounds(minNode, maxNode);
    }

    @Override
    void insert(K key, V value) {
        super.getModCount().validateParentModCount();
        super.insert(key, value);
    }

    @Override
    void remove(K key) {
        super.getModCount().validateParentModCount();
        if (!this.isKeyInBounds(key)) {
            return;
        }
        super.remove(key);
    }

    @Override
    Node<Pair<K, V>> findMin() {
        super.getModCount().validateParentModCount();
        return this.minNode;
    }

    @Override
    Node<Pair<K, V>> findMax() {
        super.getModCount().validateParentModCount();
        return this.maxNode;
    }

    @Override
    Node<Pair<K, V>> find(K key) {
        super.getModCount().validateParentModCount();
        if (!this.isKeyInBounds(key)) {
            return null;
        }
        return super.find(key);
    }

    @Override
    AVLTree<K, V> range(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        super.getModCount().validateParentModCount();
        if (super.compareKeys(fromKey, this.minNode.getNodeValue().getKey()) < 0) {
            fromKey = this.minNode.getNodeValue().getKey();
            fromInclusive = true;
        }
        if (super.compareKeys(toKey, this.maxNode.getNodeValue().getKey()) > 0) {
            toKey = this.maxNode.getNodeValue().getKey();
            toInclusive = true;
        }
        return super.range(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public void clear() {
        super.getModCount().validateParentModCount();
        //TODO
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        super.getModCount().validateParentModCount();
        //TODO get correct size
        throw new UnsupportedOperationException();
    }

    private boolean isKeyInBounds(K key) {
        return super.compareKeys(key, this.minNode.getNodeValue().getKey()) >= 0
                && super.compareKeys(key, this.maxNode.getNodeValue().getKey()) <= 0;
    }

    private void setBounds(Node<Pair<K, V>> minNode, Node<Pair<K, V>> maxNode) {
        this.setMinKey(minNode);
        this.setMaxKey(maxNode);
    }

    private void setMinKey(Node<Pair<K, V>> minNode) {
        this.minNode = minNode;
    }

    private void setMaxKey(Node<Pair<K, V>> maxNode) {
        this.maxNode = maxNode;
    }
}