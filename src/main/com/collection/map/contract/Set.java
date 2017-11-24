package main.com.collection.map.contract;

import main.com.collection.Collection;
import main.com.collection.ReverseIterable;

/**
 * Created by ChaosFire on 24.11.2017 г.
 */
public interface Set<T> extends Collection, Iterable<T>, ReverseIterable<T> {

    boolean add(T element);
    boolean contains(T element);
    boolean remove(T element);
}