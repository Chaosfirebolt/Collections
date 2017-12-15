package main.com.collection.map.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by ChaosFire on 15.12.2017 г.
 */
abstract class AbstractTreeIterator<K extends Comparable<K>, V> implements Iterator<Node<Pair<K, V>>> {

    private Node<Pair<K, V>> lastNode;
    private Stack<Node<Pair<K, V>>> stack;
    private AVLTree<K, V> tree;
    private int expectedModCount;

    AbstractTreeIterator(AVLTree<K, V> tree) {
        this.setStack(new Stack<>());
        this.setTree(tree);
        this.setExpectedModCount(tree.getModCount().getCount());
    }

    void goLeft(Node<Pair<K, V>> node) {
        while (node != null) {
            this.stack.push(node);
            node = node.getPrevNode();
        }
    }

    void goRight(Node<Pair<K, V>> node) {
        while (node != null) {
            this.stack.push(node);
            node = node.getNextNode();
        }
    }

    private void validateModificationCount() {
        if (this.expectedModCount != this.tree.getModCount().getCount()) {
            throw new ConcurrentModificationException();
        }
    }

    Node<Pair<K, V>> nextAsc() {
        this.validateModificationCount();
        Node<Pair<K, V>> next = this.stack.pop();
        this.goLeft(next.getNextNode());
        this.lastNode = next;
        return next;
    }

    Node<Pair<K, V>> nextDesc() {
        this.validateModificationCount();
        Node<Pair<K, V>> next = this.stack.pop();
        this.goRight(next.getPrevNode());
        this.lastNode = next;
        return next;
    }

    boolean hasMoreElements() {
        return this.stack.size() > 0;
    }

    void removeLast() {
        if (this.lastNode == null) {
            throw new IllegalStateException();
        }
        this.tree.remove(this.lastNode.getNodeValue().getKey());
        this.expectedModCount++;
    }

    private void setStack(Stack<Node<Pair<K, V>>> stack) {
        this.stack = stack;
    }

    private void setTree(AVLTree<K, V> tree) {
        this.tree = tree;
    }

    private void setExpectedModCount(int expectedModCount) {
        this.expectedModCount = expectedModCount;
    }
}