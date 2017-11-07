package main.com.collection.list.impl;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
abstract class AbstractListIterator<T> implements Iterator<T> {

    private Node<T> node;
    private Node<T> lastNode;
    private AbstractDoublyLinkedList<T> list;
    private ModCount modCount;
    private int expectedCount;

    AbstractListIterator(Node<T> node, AbstractDoublyLinkedList<T> list, ModCount modCount) {
        this.setNode(node);
        this.setLastNode(null);
        this.setList(list);
        this.setModCount(modCount);
        this.setExpectedCount(modCount.getCount());
    }

    void unlinkPrevNode() {
        if (this.lastNode == null) {
            throw new IllegalStateException();
        }
        this.list.unlink(this.lastNode);
        this.expectedCount++;
    }

    void validateModCount() {
        if (this.modCount.getCount() != this.expectedCount) {
            throw new ConcurrentModificationException();
        }
    }

    Node<T> getNode() {
        return this.node;
    }

    void setNode(Node<T> node) {
        this.node = node;
    }

    void setLastNode(Node<T> lastNode) {
        this.lastNode = lastNode;
    }

    private void setList(AbstractDoublyLinkedList<T> list) {
        this.list = list;
    }

    private void setModCount(ModCount modCount) {
        this.modCount = modCount;
    }

    private void setExpectedCount(int expectedCount) {
        this.expectedCount = expectedCount;
    }
}