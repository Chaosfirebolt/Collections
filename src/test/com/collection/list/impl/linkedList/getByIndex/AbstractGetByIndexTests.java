package test.com.collection.list.impl.linkedList.getByIndex;

import main.com.collection.list.contract.LinkList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractGetByIndexTests {

    private LinkList<Integer> testList;

    AbstractGetByIndexTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    @Before
    public void initialize() {
        for (int element = 0; element < 10; element++) {
            this.testList.addLast(element);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexIsNegative_ShouldThrowException() {
        this.testList.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void indexIsTooBig_ShouldThrowException() {
        this.testList.get(10);
    }

    @Test
    public void indexIsValid_ShouldReturnRight() {
        int expected = 5;
        int actual = this.testList.get(5);
        assertEquals(expected, actual);
    }

    @Test
    public void indexIsValid_ShouldReturnRight_Test2() {
        int expected = 0;
        int actual = this.testList.get(0);
        assertEquals(expected, actual);
    }

    @Test
    public void indexIsValid_ShouldReturnRight_Test3() {
        int expected = 9;
        int actual = this.testList.get(9);
        assertEquals(expected, actual);
    }
}