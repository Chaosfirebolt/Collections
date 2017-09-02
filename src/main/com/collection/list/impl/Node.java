package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
class Node<T> {

    private T value;
    private Node<T> prev;
    private Node<T> next;

    Node(T value) {
        this.setValue(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    T getValue() {
        return this.value;
    }

    private void setValue(T value) {
        this.value = value;
    }

    Node<T> getPrev() {
        return this.prev;
    }

    void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    Node<T> getNext() {
        return this.next;
    }

    void setNext(Node<T> next) {
        this.next = next;
    }
}