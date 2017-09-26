package test.com.collection.list.impl.linkedList.removeFirst;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractRemoveFirstTests {

    private static final int POSITION = 0;

    private LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractRemoveFirstTests(LinkList<Integer> testList) {
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
        this.testList.removeFirst();
    }

    @Test
    public void removeOneElement_ShouldBeRemoved() {
        this.addElements();

        this.testList.removeFirst();
        this.copyList.remove(POSITION);

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void removeOneElement_ShouldBeRight() {
        this.addElements();

        int expected = 0;
        int actual = this.testList.removeFirst();

        assertEquals(expected, actual);
    }

    @Test
    public void removeNineElements_ShouldBeRemoved() {
        this.addElements();

        for (int i = 0; i < 9; i++) {
            this.testList.removeFirst();
            this.copyList.remove(POSITION);
        }

        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void removeNineElements_ShouldBeRight() {
        this.addElements();

        for (int expected = 0; expected < 9; expected++) {
            int actual = this.testList.removeFirst();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void removeAllElements_ShouldBeEmpty() {
        this.addElements();
        String expected = "";

        for (int i = 0; i < 10; i++) {
            this.testList.removeFirst();
        }

        assertEquals(expected, Helper.printCollection(this.testList));
    }

    @Test
    public void removeAllElements_ShouldBeRight() {
        this.addElements();

        for (int expected = 0; expected < 10; expected++) {
            int actual = this.testList.removeFirst();
            assertEquals(expected, actual);
        }
    }
}