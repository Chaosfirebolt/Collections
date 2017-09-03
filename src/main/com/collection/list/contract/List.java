package main.com.collection.list.contract;

import main.com.collection.Collection;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
public interface List<T> extends Collection {

    void add(T element);
    T get(int index);
    T remove(int index);
    boolean remove(T element);
    boolean contains(T element);
}