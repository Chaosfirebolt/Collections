package main.com.collection.list.contract;

/**
 * Created by ChaosFire on 30.6.2017 г
 */
public interface LinkList<T> extends List<T>, Iterable<T> {

    void addFirst(T element);
    void addLast(T element);
    T removeFirst();
    T removeLast();
    T getFirst();
    T getLast();
}