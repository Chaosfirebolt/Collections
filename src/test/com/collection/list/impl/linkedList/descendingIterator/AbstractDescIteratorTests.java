package test.com.collection.list.impl.linkedList.descendingIterator;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
abstract class AbstractDescIteratorTests {

    void assertCollection(Iterator<Integer> iterator, Integer expected) {
        while (iterator.hasNext()) {
            Integer actual = iterator.next();
            assertEquals(expected, actual);
            expected--;
        }
    }
}