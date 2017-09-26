package main.com.collection;

import java.util.Iterator;

/**
 * Created by ChaosFire on 26.9.2017 г.
 */
public interface ReverseIterable<T> {

    Iterator<T> descendingIterator();
}