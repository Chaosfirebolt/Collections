package test.com.collection.list.impl.arrayList.iterator;

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
public class ArrayListIteratorTests {

    private ArrayList<Integer> list;
    private LinkedList<Integer> copyList;

    public ArrayListIteratorTests() {
        this.list = new ArrayList<>();
        this.copyList = new LinkedList<>();
    }

    @Before
    public void setConditions() {
        for (int i = 0; i < 100; i++) {
            this.list.add(i);
            this.copyList.add(i);
        }
    }

    @Test
    public void listHasElements_shouldIterateInCorrectOrder() {
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.list));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void removeElementDuringIteration_ShouldThrowException() {
        for (Integer integer : this.list) {
            this.list.remove(integer);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void addElementDuringIteration_ShouldThrowException() {
        for (Integer integer : this.list) {
            int element = integer + 5;
            this.list.add(element);
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void clearListDuringIteration_ShouldThrowException() {
        for (Integer integer : this.list) {
            if (integer == 10) {
                this.list.clear();
            }
        }
    }

    @Test
    public void iteratorRemoveShouldRemoveCorrectElement_Test1() {
        Iterator<Integer> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 31) {
                iterator.remove();
                this.copyList.remove(element);
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }

    @Test
    public void iteratorRemoveShouldRemoveCorrectElement_Test2() {
        Iterator<Integer> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 0) {
                iterator.remove();
                this.copyList.remove(element);
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }

    @Test
    public void iteratorRemoveShouldRemoveCorrectElement_Test3() {
        Iterator<Integer> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == 99) {
                iterator.remove();
                this.copyList.remove(element);
            }
        }
        assertEquals(Helper.printCollection(this.list), Helper.printCollection(this.copyList));
    }
}