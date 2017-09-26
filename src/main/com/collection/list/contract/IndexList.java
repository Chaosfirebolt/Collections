package main.com.collection.list.contract;

/**
 * Created by ChaosFire on 15.9.2017 г.
 */
public interface IndexList<T> extends List<T>, Iterable<T> {

    T set(int index, T element);
    void add(int index, T element);
    IndexList<T> subList(int fromIndex, int toIndex);
    IndexList<T> subListCopy(int fromIndex, int toIndex);
}