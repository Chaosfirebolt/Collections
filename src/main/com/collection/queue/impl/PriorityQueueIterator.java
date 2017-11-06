package main.com.collection.queue.impl;

import java.util.Iterator;

/**
 * Created by ChaosFire on 6.11.2017 г.
 */
class PriorityQueueIterator<T> implements Iterator<T> {

    private static final String ERROR_MSG = "Priority queue iterator doesn't allow element removal during iteration.";

    private Iterator<T> heapIterator;

    PriorityQueueIterator(Iterator<T> heapIterator) {
        this.heapIterator = heapIterator;
    }

    @Override
    public boolean hasNext() {
        return this.heapIterator.hasNext();
    }

    @Override
    public T next() {
        return this.heapIterator.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException(ERROR_MSG);
    }
}