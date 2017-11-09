package test.com.collection.list.impl.arrayList.descIterator;

import main.com.collection.list.impl.ArrayList;
import main.com.collection.list.impl.LinkedList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.ConcurrentModificationException;
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

    @Test(expected = ConcurrentModificationException.class)
    public void addDuringIteration_ShouldThrowException() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.list.add(0);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeDuringIteration_ShouldThrowException() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.list.remove(0);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void clearDuringIteration_ShouldThrowException() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (element == 15) {
                this.list.clear();
            }
        }
    }

    @Test
    public void iteratorRemove_ShouldRemoveCorrectElement_Test1() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 0) {
                this.copyList.remove(element);
                iterator.remove();
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }

    @Test
    public void iteratorRemove_ShouldRemoveCorrectElement_Test2() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 57) {
                this.copyList.remove(element);
                iterator.remove();
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }

    @Test
    public void iteratorRemove_ShouldRemoveCorrectElement_Test3() {
        Iterator<Integer> iterator = this.list.descendingIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 99) {
                this.copyList.remove(element);
                iterator.remove();
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }
}