package test.com.collection.list.impl.linkedList.iterator;

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
public abstract class AbstractIteratorTests {

    private LinkList<Integer> testList;

    AbstractIteratorTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    private void addElements(int count) {
        for (int element = 0; element < count; element++) {
            this.testList.add(element);
        }
    }

    private IndexList<Integer> expectedList(int count, int doNotAdd) {
        IndexList<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (i != doNotAdd) {
                list.add(i);
            }
        }
        return list;
    }

    @Test
    public void shouldIterateInRightOrder_Test1() {
        this.addElements(10);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void shouldIterateInRightOrder_Test2() {
        this.addElements(17);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void shouldIterateInRightOrder_Test3() {
        this.addElements(31);
        Integer expected = 0;
        for (Integer actual : this.testList) {
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test(expected = IllegalStateException.class)
    public void iteratorRemoveCalledBeforeNextCall_ShouldThrowException() {
        this.addElements(10);
        Iterator<Integer> iterator = this.testList.iterator();
        iterator.remove();
    }

    @Test
    public void iteratorRemoveElement_ShouldRemoveCorrect() {
        int count = 10;
        this.addElements(count);
        int toRemove = 7;
        Iterator<Integer> iterator = this.testList.iterator();
        while (iterator.hasNext()) {
            int curr = iterator.next();
            if (curr == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(this.testList), Helper.printCollection(expected));
    }

    @Test
    public void iteratorRemoveFirstElement_ShouldRemoveCorrect() {
        int count = 0;
        this.addElements(count);
        int toRemove = 7;
        Iterator<Integer> iterator = this.testList.iterator();
        while (iterator.hasNext()) {
            int curr = iterator.next();
            if (curr == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(this.testList), Helper.printCollection(expected));
    }

    @Test
    public void iteratorRemoveLastElement_ShouldRemoveCorrect() {
        int count = 10;
        this.addElements(count);
        int toRemove = 9;
        Iterator<Integer> iterator = this.testList.iterator();
        while (iterator.hasNext()) {
            int curr = iterator.next();
            if (curr == toRemove) {
                iterator.remove();
            }
        }
        IndexList<Integer> expected = this.expectedList(count, toRemove);
        assertEquals(Helper.printCollection(this.testList), Helper.printCollection(expected));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addFirstDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            if (integer != null) {
                this.testList.addFirst(100);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addLastDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            if (integer != null) {
                this.testList.addLast(100);
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeFirstDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            if (integer != null) {
                this.testList.removeFirst();
            }
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeLastDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            if (integer != null) {
                this.testList.removeLast();
            }
        }
    }

    @Test
    public void getFirstDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.getFirst();
        }
    }

    @Test
    public void getLastDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.getLast();
        }
    }

    @Test
    public void getAtIndexDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.get(0);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeAtIndexDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.remove(1);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeObjectDuringIteration_ShouldThrowException() {
        this.addElements(10);
        Integer obj = 4;
        for (Integer integer : this.testList) {
            this.testList.remove(obj);
        }
    }

    @Test
    public void removeNonExistentObjectDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        Integer obj = 100;
        for (Integer integer : this.testList) {
            this.testList.remove(obj);
        }
    }

    @Test
    public void callContainsMethodDuringIteration_ShouldContinueIteration() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.contains(4);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void clearDuringIteration_ShouldThrowException() {
        this.addElements(10);
        for (Integer integer : this.testList) {
            this.testList.clear();
        }
    }
}