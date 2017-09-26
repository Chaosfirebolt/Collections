package main.com.collection.list.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 19.9.2017 г.
 */
abstract class AbstractArrayIterator<T> implements Iterator<T> {

    private T[] array;
    private int currentIndex;
    private int startIndex;
    private int endIndex;

    AbstractArrayIterator(T[] array, int currentIndex, int startIndex, int endIndex) {
        this.setArray(array);
        this.setCurrentIndex(currentIndex);
        this.setStartIndex(startIndex);
        this.setEndIndex(endIndex);
    }

    boolean isIndexValid() {
        return this.currentIndex >= this.startIndex && this.currentIndex < this.endIndex;
    }

    void incrementCurrentIndex() {
        this.currentIndex++;
    }

    void decrementCurrentIndex() {
        this.currentIndex--;
    }

    T getCurrentValue() {
        return this.array[this.currentIndex];
    }

    private void setArray(T[] array) {
        this.array = array;
    }

    private void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    private void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    private void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }
}