package test.com.collection.list.impl.linkedList.descendingIterator;

import main.com.collection.list.contract.IndexList;
import main.com.collection.list.contract.LinkList;
import main.com.collection.list.impl.ArrayList;
import org.junit.Test;
import test.util.Helper;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
@SuppressWarnings("unused")
public abstract class AbstractDescIteratorTests {

    private LinkList<Integer> testList;

    AbstractDescIteratorTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    private void assertCollection(Iterator<Integer> iterator, Integer expected) {
        while (iterator.hasNext()) {
            Integer actual = iterator.next();
            assertEquals(expected, actual);
            expected--;
        }
    }

    private void addElements(int count) {
        for (int element = 0; element < count; element++) {
            this.testList.add(element);
        }
    }

    private IndexList<Integer> expectedList(int count, int doNotAdd) {
        IndexList<Integer> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            if (i != doNotAdd) {
                list.add(i);
            }
        }
        return list;
    }

    private void test(int count) {
        this.addElements(count);
        Integer expected = count - 1;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        this.assertCollection(iterator, expected);
    }

    @Test
    public void shouldIterateInRightOrder_Test1() {
        int count = 10;
        this.test(count);
    }

    @Test
    public void shouldIterateInRightOrder_Test2() {
        int count = 17;
        this.test(count);
    }

    @Test
    public void shouldIterateInRightOrder_Test3() {
        int count = 31;
        this.test(count);
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveCalledBeforeNextCall_ShouldThrowException() {
        this.addElements(10);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        iterator.remove();
    }

    @Test
    public void iteratorRemoveElement_ShouldRemoveCorrect() {
        int count = 100;
        this.addElements(count);
        int toRemove = 3;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (element == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(expected), Helper.printCollection(this.testList));
    }

    @Test
    public void iteratorRemoveFirstElement_ShouldRemoveCorrect() {
        int count = 100;
        this.addElements(count);
        int toRemove = 0;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (element == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(expected), Helper.printCollection(this.testList));
    }

    @Test
    public void iteratorRemoveLastElement_ShouldRemoveCorrect() {
        int count = 100;
        this.addElements(count);
        int toRemove = 99;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            int element = iterator.next();
            if (element == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(expected), Helper.printCollection(this.testList));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addFirstDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            if (iterator.next() != null) {
                this.testList.addFirst(100);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addLastDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            if (iterator.next() != null) {
                this.testList.addLast(100);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeFirstDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            if (iterator.next() != null) {
                this.testList.removeFirst();
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeLastDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            if (iterator.next() != null) {
                this.testList.removeLast();
            }
        }
    }

    @Test
    public void getFirstDuringIteration_ShouldContinueIteration() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.getFirst();
        }
    }

    @Test
    public void getLastDuringIteration_ShouldContinueIteration() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.getLast();
        }
    }

    @Test
    public void getAtIndexDuringIteration_ShouldContinueIteration() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.get(0);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeAtIndexDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.remove(1);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeObjectDuringIteration_ShouldThrowException() {
        this.addElements(100);
        Integer obj = 4;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.remove(obj);
        }
    }

    @Test
    public void removeNonExistentObjectDuringIteration_ShouldContinueIteration() {
        this.addElements(100);
        Integer obj = 1000;
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.remove(obj);
        }
    }

    @Test
    public void callContainsMethodDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.contains(4);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void clearDuringIteration_ShouldThrowException() {
        this.addElements(10);
        Iterator<Integer> iterator = this.testList.descendingIterator();
        while (iterator.hasNext()) {
            iterator.next();
            this.testList.clear();
        }
    }
}