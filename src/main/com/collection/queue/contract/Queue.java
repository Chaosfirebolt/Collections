package main.com.collection.queue.contract;

import main.com.collection.Collection;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public interface Queue<T> extends Collection, Iterable<T> {

    void insert(T element);
    T peek();
    T poll();
}