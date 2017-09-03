package main.com.collection.queue.impl;

import main.com.collection.AbstractCollection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
abstract class AbstractMinBinaryHeap<T> extends AbstractCollection {

    private static final int DEFAULT_CAPACITY = 16;

    private List<T> heap;
    private Comparator<T> comparator;

    AbstractMinBinaryHeap(int capacity, Class<T> clazz, Comparator<T> comparator) {
        this.validateComparison(clazz, comparator);
        this.setHeap(new ArrayList<>(capacity));
        this.setComparator(this.createComparator(clazz, comparator));
    }

    private void validateComparison(Class<T> clazz, Comparator<T> comparator) {
        if (!Comparable.class.isAssignableFrom(clazz) && comparator == null) {
            throw new IllegalStateException("Class should implement the Comparable interface, or valid custom Comparator should be passed!");
        }
    }

    private Comparator<T> createComparator(Class<T> clazz, Comparator<T> comparator) {
        if (comparator == null) {
            Method method;
            try {
                method = clazz.getMethod("compareTo", clazz);
            } catch (NoSuchMethodException exc) {
                throw new IllegalArgumentException("Method compareTo not found!");
            }
            return  (x, y) -> {
                Object invocationResult;
                try {
                    invocationResult = method.invoke(x, y);
                } catch (IllegalAccessException | InvocationTargetException exc) {
                    throw new IllegalArgumentException("Comparison method could not be invoked!");
                }
                return (Integer) invocationResult;
            };
        } else {
            return comparator;
        }
    }

    AbstractMinBinaryHeap(int capacity, Class<T> clazz) {
        this(capacity, clazz, null);
    }

    AbstractMinBinaryHeap(Class<T> clazz, Comparator<T> comparator) {
        this(DEFAULT_CAPACITY, clazz, comparator);
    }

    AbstractMinBinaryHeap(Class<T> clazz) {
        this(DEFAULT_CAPACITY, clazz, null);
    }

    void clearHeap() {
        this.heap.clear();
    }

    void add(T element) {
        this.heap.add(element);
    }

    void deleteFirst() {
        int last = this.heap.size() - 1;
        this.swap(0, last);
        this.heap.remove(last);
    }

    void heapUp() {
         int index = this.heap.size() - 1;
         int parent = this.getParentIndex(index);
         while (index > 0 && this.compare(index, parent) < 0) {
             this.swap(index, parent);
             index = parent;
             parent = this.getParentIndex(index);
         }
    }

    void heapDown() {
        int index = 0;
        int last = super.getSize() / 2;
        while (index < last) {
            int leftChild = this.getLeftChildIndex(index);
            int rightChild = this.getRightChildIndex(index);
            int lastIndex = this.heap.size() - 1;

            if (leftChild > lastIndex) {
                break;
            }
            int child;
            if (rightChild > lastIndex) {
                child = leftChild;
            } else {
                child = this.compare(leftChild, rightChild) < 0 ? leftChild : rightChild;
            }
            if (this.compare(index, child) <= 0) {
                break;
            }
            this.swap(index, child);
            index = child;
        }
    }

    T getFirst() {
        return this.heap.get(0);
    }

    void validateNotNull(T element) {
        if (element == null) {
            throw new NullPointerException("Element can not be null!");
        }
    }

    void validateHasElements() {
        if (this.heap.size() == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
    }

    Iterator<T> heapIterator() {
        return this.heap.iterator();
    }

    private int compare(int i, int j) {
        T obj = this.heap.get(i);
        T other = this.heap.get(j);
        return this.comparator.compare(obj, other);
    }

    private void swap(int i, int j) {
        T temp = this.heap.get(i);
        this.heap.set(i, this.heap.get(j));
        this.heap.set(j, temp);
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return (2 * index) + 1;
    }

    private int getRightChildIndex(int index) {
        return (2 * index) + 2;
    }

    private void setHeap(List<T> heap) {
        this.heap = heap;
    }

    private void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
}