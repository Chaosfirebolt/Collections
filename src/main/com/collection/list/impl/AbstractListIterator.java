package main.com.collection.list.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
abstract class AbstractListIterator<T> implements Iterator<T> {

    private Node<T> node;

    AbstractListIterator(Node<T> node) {
        this.setNode(node);
    }

    Node<T> getNode() {
        return this.node;
    }

    void setNode(Node<T> node) {
        this.node = node;
    }
}