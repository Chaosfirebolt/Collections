package test.com.collection.list.impl.linkedList.getFirst;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractGetFirstTests {

    private LinkList<Integer> testList;

    AbstractGetFirstTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    @Test(expected = NoSuchElementException.class)
    public void listIsEmpty_ShouldThrowException() {
        this.testList.getFirst();
    }

    @Test
    public void listHasOneElement_ShouldReturnIt() {
        this.testList.addFirst(0);
        int expected = 0;
        int actual = this.testList.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void listHasManyElements_ShouldReturnRight() {
        for (int i = 0; i < 9; i++) {
            this.testList.addLast(i);
        }
        int expected = 0;
        int actual = this.testList.getFirst();
        assertEquals(expected, actual);
    }

    @Test
    public void listHasManyElements_ShouldReturnRight_Test2() {
        for (int i = 0; i < 9; i++) {
            this.testList.addFirst(i);
        }
        int expected = 8;
        int actual = this.testList.getFirst();
        assertEquals(expected, actual);
    }
}