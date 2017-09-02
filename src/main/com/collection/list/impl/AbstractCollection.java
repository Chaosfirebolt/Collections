package main.com.collection.list.impl;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
abstract class AbstractCollection {

    private static final int INITIAL_SIZE = 0;

    private int size;

    AbstractCollection() {
        super();
        this.setSize(INITIAL_SIZE);
    }

    void incrementSize() {
        this.size++;
    }

    void decrementSize() {
        this.size--;
    }

    void clearSize() {
        this.setSize(INITIAL_SIZE);
    }

    int getSize() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }
}