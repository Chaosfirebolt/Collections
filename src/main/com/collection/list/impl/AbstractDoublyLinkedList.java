package main.com.collection.list.impl;

import main.com.collection.AbstractCollection;

import java.util.NoSuchElementException;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
abstract class AbstractDoublyLinkedList<T> extends AbstractCollection {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private ModCount modCount;

    AbstractDoublyLinkedList() {
        this.setModCount(new ModCount());
    }

    void clearConnections() {
        this.setFirstNode(null);
        this.setLastNode(null);
        this.modCount.incrementCount();
    }

    Node<T> linkFirst(T value) {
        Node<T> newNode = this.createNewNode(value);
        if (this.hasNoElements()) {
            this.addNodeNoElements(newNode);
        } else {
            this.linkFirstNode(newNode);
        }
        this.modCount.incrementCount();
        return newNode;
    }

    Node<T> linkLast(T value) {
        Node<T> newNode = this.createNewNode(value);
        if (this.hasNoElements()) {
            this.addNodeNoElements(newNode);
        } else {
            this.linkLastNode(newNode);
        }
        this.modCount.incrementCount();
        return newNode;
    }

    void unlink(Node<T> node) {
        Node<T> prev = node.getPrev();
        Node<T> next = node.getNext();
        if (prev == null && next == null) {
            this.setFirstNode(null);
            this.setLastNode(null);
            super.decrementSize();
            this.modCount.incrementCount();
            return;
        }
        if (prev == null) {
            this.unlinkFirst(next);
            super.decrementSize();
            this.modCount.incrementCount();
            return;
        }
        if (next == null) {
            this.unlinkLast(prev);
            super.decrementSize();
            this.modCount.incrementCount();
            return;
        }
        prev.setNext(next);
        next.setPrev(prev);
        super.decrementSize();
        this.modCount.incrementCount();
    }

    private void unlinkFirst(Node<T> node) {
        node.setPrev(null);
        this.setFirstNode(node);
    }

    private void unlinkLast(Node<T> node) {
        node.setNext(null);
        this.setLastNode(node);
    }

    private void linkFirstNode(Node<T> newNode) {
        Node<T> firstNode = this.firstNode;
        firstNode.setPrev(newNode);

        newNode.setNext(firstNode);
        this.setFirstNode(newNode);
    }

    private void linkLastNode(Node<T> newNode) {
        Node<T> lastNode = this.lastNode;
        lastNode.setNext(newNode);

        newNode.setPrev(lastNode);
        this.setLastNode(newNode);
    }

    private void addNodeNoElements(Node<T> newNode) {
        this.setFirstNode(newNode);
        this.setLastNode(newNode);
    }

    Node<T> removeNodeByIndex(int index) {
        Node<T> node = this.findNodeByIndex(index);
        this.unlink(node);
        return node;
    }

    Node<T> findNodeByIndex(int index) {
        int lasIndex = super.getSize() - 1;
        Node<T> node;
        if (index < lasIndex / 2) {
            int count = 0;
            node = this.firstNode;
            while (count < index) {
                node = node.getNext();
                count++;
            }
        } else {
            int count = lasIndex;
            node = this.lastNode;
            while (count > index) {
                node = node.getPrev();
                count--;
            }
        }
        return node;
    }

    void validateIndexExists(int index) {
        if (index < 0 || index >= super.getSize()) {
            throw new IndexOutOfBoundsException();
        }
    }

    void validateHasElements() {
        if (this.hasNoElements()) {
            throw new NoSuchElementException();
        }
    }

    private boolean hasNoElements() {
        return super.getSize() == 0;
    }

    private Node<T> createNewNode(T value) {
        return new Node<>(value);
    }

    Node<T> getFirstNode() {
        return this.firstNode;
    }

    private void setFirstNode(Node<T> firstNode) {
        this.firstNode = firstNode;
    }

    Node<T> getLastNode() {
        return this.lastNode;
    }

    private void setLastNode(Node<T> lastNode) {
        this.lastNode = lastNode;
    }

    ModCount getModCount() {
        return this.modCount;
    }

    private void setModCount(ModCount modCount) {
        this.modCount = modCount;
    }
}