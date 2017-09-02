package main.com.collection.list.contract;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
public interface List<T> {

    void add(T element);
    T get(int index);
    T remove(int index);
    boolean remove(T element);
    boolean contains(T element);
    void clear();
    int size();
}