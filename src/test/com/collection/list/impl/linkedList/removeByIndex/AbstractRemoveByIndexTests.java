package test.com.collection.list.impl.linkedList.removeByIndex;

import main.com.collection.list.contract.LinkList;
import org.junit.Before;
import org.junit.Test;
import test.util.Helper;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractRemoveByIndexTests {

    private LinkList<Integer> testList;
    private ArrayList<Integer> copyList;

    AbstractRemoveByIndexTests(LinkList<Integer> testList) {
        this.testList = testList;
        this.copyList = new ArrayList<>();
    }

    @Before
    public void initialize() {
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
            this.copyList.add(element);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexIsNegative_ShouldThrowException() {
        this.testList.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexIsEqualToSize_ShouldThrowException() {
        this.testList.remove(10);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexIsHigherThanSize_ShouldThrowException() {
        this.testList.remove(21);
    }

    @Test
    public void indexIsValid_ShouldReturnRight_Test1() {
        int expected = 0;
        int actual = this.testList.remove(0);
        assertEquals(expected, actual);
    }

    @Test
    public void indexIsValid_ShouldReturnRight_Test2() {
        int expected = 9;
        int actual = this.testList.remove(9);
        assertEquals(expected, actual);
    }

    @Test
    public void indexIsValid_ShouldReturnRight_Test3() {
        int expected = 7;
        int actual = this.testList.remove(7);
        assertEquals(expected, actual);
    }

    @Test
    public void indexIsValid_ShouldBeRemoved_Test1() {
        int index = 0;
        this.testList.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void indexIsValid_ShouldBeRemoved_Test2() {
        int index = 9;
        this.testList.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }

    @Test
    public void indexIsValid_ShouldBeRemoved_Test3() {
        int index = 3;
        this.testList.remove(index);
        this.copyList.remove(index);
        assertEquals(Helper.printCollection(this.copyList), Helper.printCollection(this.testList));
    }
}