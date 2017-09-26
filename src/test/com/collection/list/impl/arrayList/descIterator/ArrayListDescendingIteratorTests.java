package test.com.collection.list.impl.arrayList.descIterator;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 24.9.2017 г.
 */
public class ArrayListDescendingIteratorTests {

    private ArrayList<Integer> list;
    private LinkedList<Integer> copyList;

    public ArrayListDescendingIteratorTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Before
    public void setConditions() {
        for (int element = 999; element >= 0; element--) {
            this.list.add(element);
            this.copyList.add(element);
        }
    }

    @Test
    public void listHasElements_ShouldIterateInCorrectOrder() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            int expected = this.copyList.removeLast();
            int actual = iterator.next();
            assertEquals(expected, actual);
        }
    }
}