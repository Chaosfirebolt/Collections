package test.com.collection.list.impl.linkedList.size;

import main.com.collection.list.contract.LinkList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ChaosFire on 2.9.2017 г.
 */
public abstract class AbstractSizeTests {

    private LinkList<Integer> testList;

    AbstractSizeTests(LinkList<Integer> testList) {
        this.testList = testList;
    }

    private void addElements() {
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
        }
    }

    @Test
    public void sizeShouldBeZeroAfterInitialization() {
        int expected = 0;
        int actual = this.testList.size();
        assertEquals(expected, actual);
    }

    @Test
    public void sizeShouldBeRightAfterEachElementAdding() {
        int expected = 1;
        for (int element = 0; element < 10; element++) {
            this.testList.add(element);
            int actual = this.testList.size();
            assertEquals(expected, actual);
            expected++;
        }
    }

    @Test
    public void sizeShouldBeRightAfterEachRemoval_Test1() {
        this.addElements();
        int expected = this.testList.size();
        int size = this.testList.size();
        for (int i = 0; i < size; i++) {
            this.testList.removeFirst();
            expected--;
            int actual = this.testList.size();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void sizeShouldBeRightAfterEachRemoval_Test2() {
        this.addElements();
        int expected = this.testList.size();
        int size = this.testList.size();
        for (int i = 0; i < size; i++) {
            this.testList.removeLast();
            expected--;
            int actual = this.testList.size();
            assertEquals(expected, actual);
        }
    }
}