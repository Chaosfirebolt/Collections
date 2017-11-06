package main.com.collection.queue.impl;

import main.com.collection.queue.contract.Queue;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by ChaosFire on 3.9.2017 г.
 */
public class PriorityQueue<T> extends AbstractMinBinaryHeap<T> implements Queue<T> {

    public PriorityQueue(int capacity, Class<T> clazz, Comparator<T> comparator) {
        super(capacity, clazz, comparator);
    }

    public PriorityQueue(int capacity, Class<T> clazz) {
        super(capacity, clazz);
    }

    public PriorityQueue(Class<T> clazz, Comparator<T> comparator) {
        super(clazz, comparator);
    }

    public PriorityQueue(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void insert(T element) {
        super.validateNotNull(element);
        super.add(element);
        super.incrementSize();
        super.heapUp();
    }

    @Override
    public T peek() {
        super.validateHasElements();
        return super.getFirst();
    }

    @Override
    public T poll() {
        super.validateHasElements();
        T value = super.getFirst();
        super.deleteFirst();
        super.decrementSize();
        super.heapDown();
        return value;
    }

    @Override
    public void clear() {
        super.clearHeap();
        super.clearSize();
    }

    @Override
    public int size() {
        return super.getSize();
    }

    @Override
    public Iterator<T> iterator() {
        return new PriorityQueueIterator<>(super.heapIterator());
    }
}