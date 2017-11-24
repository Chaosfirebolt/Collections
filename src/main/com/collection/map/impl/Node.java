package main.com.collection.map.impl;

/**
 * Created by ChaosFire on 9.11.2017 г.
 */
class Node<T> {

    private static final int DEFAULT_HEIGHT = 1;

    private T nodeValue;
    private Node<T> prevNode;
    private Node<T> nextNode;
    private int height;

    Node(T nodeValue) {
        this.setNodeValue(nodeValue);
        this.setHeight(DEFAULT_HEIGHT);
    }

    @Override
    public String toString() {
        return this.nodeValue.toString();
    }

    T getNodeValue() {
        return this.nodeValue;
    }

    void setNodeValue(T nodeValue) {
        this.nodeValue = nodeValue;
    }

    Node<T> getPrevNode() {
        return this.prevNode;
    }

    void setPrevNode(Node<T> prevNode) {
        this.prevNode = prevNode;
    }

    Node<T> getNextNode() {
        return this.nextNode;
    }

    void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    int getHeight() {
        return this.height;
    }

    void setHeight(int height) {
        this.height = height;
    }
}