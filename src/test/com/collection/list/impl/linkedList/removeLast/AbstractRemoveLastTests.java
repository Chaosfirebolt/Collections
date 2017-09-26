package test.com.collection.list.impl.linkedList.removeLast;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractRemoveLastTests {

    private LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractRemoveLastTests(LinkList<Integer> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    private void addElements() {
        for (int element = 0; element < 10; element++) {
            this.testList.addLast(element);
            this.copyList.add(element);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void emptyList_ShouldThrowException() {
        this.testList.removeLast();
    }

    @Test
    public void removeOneElement_ShouldBeRemoved() {
        this.addElements();

        this.testList.removeLast();
        this.copyList.remove(this.copyList.size() - 1);

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void removeOneElement_ShouldBeRight() {
        this.addElements();

        int expected = 9;
        int actual = this.testList.removeLast();

        assertEquals(expected, actual);
    }

    @Test
    public void removeNineElements_ShouldBeRemoved() {
        this.addElements();

        for (int i = 0; i < 9; i++) {
            this.testList.removeLast();
            this.copyList.remove(this.copyList.size() - 1);
        }

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void removeNineElements_ShouldBeRight() {
        this.addElements();

        for (int expected = 9; expected >= 0; expected--) {
            int actual = this.testList.removeLast();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void removeAllElements_ShouldBeEmpty() {
        this.addElements();
        String expected = "";

        for (int i = 0; i < 10; i++) {
            this.testList.removeLast();
        }

        assertEquals(expected, Helper.printCollection(this.testList));
    }

    @Test
    public void removeAllElements_ShouldBeRight() {
        this.addElements();

        for (int expected = 9; expected >= 0; expected--) {
            int actual = this.testList.removeLast();
            assertEquals(expected, actual);
        }
    }
}