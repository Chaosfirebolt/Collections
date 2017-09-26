package main.com.collection.list.impl;

import main.com.collection.list.contract.LinkList;

import java.util.Iterator;

/**
 * Created by ChaosFire on 27.8.2017 г.
 */
public class LinkedList<T> extends AbstractDoublyLinkedList<T> implements LinkList<T> {

    public LinkedList() {
        super();
    }

    @Override
    public void addFirst(T element) {
        super.linkFirst(element);
        super.incrementSize();
    }

    @Override
    public void addLast(T element) {
        super.linkLast(element);
        super.incrementSize();
    }

    @Override
    public T removeFirst() {
        super.validateHasElements();
        Node<T> node = super.getFirstNode();
        super.unlink(node);
        super.decrementSize();
        return node.getValue();
    }

    @Override
    public T removeLast() {
        super.validateHasElements();
        Node<T> node = super.getLastNode();
        super.unlink(node);
        super.decrementSize();
        return node.getValue();
    }

    @Override
    public T getFirst() {
        super.validateHasElements();
        return super.getFirstNode().getValue();
    }

    @Override
    public T getLast() {
        super.validateHasElements();
        return super.getLastNode().getValue();
    }

    @Override
    public void add(T element) {
        this.addLast(element);
    }

    @Override
    public T get(int index) {
        super.validateIndexExists(index);
        return super.findNodeByIndex(index).getValue();
    }

    @Override
    public T remove(int index) {
        super.validateIndexExists(index);
        return super.removeNodeByIndex(index).getValue();
    }

    @Override
    public boolean remove(T element) {
        boolean removed = false;
        Node<T> node = super.getFirstNode();
        while (node != null) {
            if (element.equals(node.getValue())) {
                removed = true;
                super.unlink(node);
                super.decrementSize();
                break;
            }
            node = node.getNext();
        }
        return removed;
    }

    @Override
    public boolean contains(T element) {
        boolean contains = false;
        for (T curr : this) {
            if (curr.equals(element)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public void clear() {
        super.clearConnections();
        super.clearSize();
    }

    @Override
    public int size() {
        return super.getSize();
    }

    @Override
    public Iterator<T> iterator() {
        return new AscListIterator<>(super.getFirstNode());
    }

    @Override
    public Iterator<T> descendingIterator() {
        return new DescListIterator<>(super.getLastNode());
    }
}