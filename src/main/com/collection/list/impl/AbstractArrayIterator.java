package main.com.collection.list.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 19.9.2017 г.
 */
abstract class AbstractArrayIterator<T> implements Iterator<T> {

    private ArrayList<T> arrayList;
    private int currentIndex;
    private int startIndex;
    private int endIndex;
    private int expectedModCount;

    AbstractArrayIterator(ArrayList<T> arrayList, int currentIndex, int startIndex, int endIndex, int expectedModCount) {
        this.setArrayList(arrayList);
        this.setCurrentIndex(currentIndex);
        this.setStartIndex(startIndex);
        this.setEndIndex(endIndex);
        this.setExpectedModCount(expectedModCount);
    }

    boolean isIndexValid() {
        return this.currentIndex >= this.startIndex && this.currentIndex < this.endIndex;
    }

    void removePrevious(int toPrev) {
        try {
            this.arrayList.remove(this.currentIndex + toPrev);
            this.expectedModCount++;
        } catch (IndexOutOfBoundsException exc) {
            throw new IllegalStateException();
        }
    }

    void validateState() {
        this.arrayList.getModCount().validate(this.expectedModCount);
    }

    void changeCurrentIndex(int change) {
        this.currentIndex += change;
    }

    T getCurrentValue() {
        return this.arrayList.getArray()[this.currentIndex];
    }

    private void setArrayList(ArrayList<T> arrayList) {
        this.arrayList = arrayList;
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

    private void setExpectedModCount(int expectedModCount) {
        this.expectedModCount = expectedModCount;
    }
}